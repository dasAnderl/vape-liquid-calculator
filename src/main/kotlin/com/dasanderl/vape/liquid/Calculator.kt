package com.dasanderl.vape.liquid

import com.charleskorn.kaml.Yaml
import java.lang.Exception

object Calculator {

    @JvmStatic
    fun main(args: Array<String>) {
        calc(Stash.get(), Recipe.all())
    }

    fun reduceStashByRecipeAmounts(recipeAmounts: List<RecipeAmount>, stash: Stash = Stash.get()): Stash =
        recipeAmounts
            .map { Recipe.byName(it.recipeName) to it.amountLiquid }
            .map { FlavorsForRecipe.get(it.first, it.second) }
            .flatMap { it.flavors}
            .let {
                stash - it
            }.also { reducedStash ->
                reducedStash
                    .flavorAmounts
                    .find { it.ml < 0.0 }
                    ?.also {
                        throw Exception("the recipes would reduce some flavors under 0\n${Yaml.pretty(reducedStash)}")
                    }

            }

    fun calc(stash: Stash, recipes: List<Recipe>): List<RecipeResult> {

        var stash_ = stash.copy()
        return recipes.map { recipe ->
            calcRecipe(recipe, stash_)
                .also { stash_ = it.remainingStash }
        }.also {
            log(Yaml.pretty(it))
        }
    }

    private fun calcRecipe(recipe: Recipe, stash: Stash): RecipeResult {

        val missingFlavors = recipe.flavours
            .map { it.flavor }
            .filterNot {
                stash.findFlavorStash(it).let {
                    it != null && it.ml > 0
                }
            }

        if (missingFlavors.isNotEmpty()) {
            return RecipeResult(recipe.name, missingFlavors = missingFlavors, remainingStash = stash)
        }

        //the maximum amount of aroma for each flavor in stash
        val maxFlavorStashes = recipe.flavours
            .map { it to stash.findFlavorStash(it.flavor) }
            .map {
                val amount = (it.second!!.ml * 10 / it.first.gramsPer10ml).round(2)
                FlavorAmountMl(it.first.flavor, amount)
            }
            .sortedBy { it.ml }

        // the max ml of aroma mixable is the lowest number of above values
        val amountMixableMl = maxFlavorStashes.minOf { it.ml }
        val amountMixableLiquidMl = (amountMixableMl * ( 100 / recipe.mixingPct)).round(2)
        //  get the amount of flavors needed for this recipe and the amount
        val flavorsForRecipe = FlavorsForRecipe.get(recipe, amountMixableLiquidMl)
        return RecipeResult(
            recipe.name,
            amountMixableMl,
            amountMixableLiquidMl,
            mixableAmountPerFlavor = maxFlavorStashes,
            remainingStash = stash - flavorsForRecipe.flavors,
        )
    }
}