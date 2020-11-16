package com.dasanderl.vape.liquid

import com.charleskorn.kaml.Yaml

object Calculator {

    @JvmStatic
    fun main(args: Array<String>) {
        calc(Stash.get(), Recipe.all())
    }

    fun calc(stash: Stash, recipes: List<Recipe>): List<RecipeResult> {

        val stash_ = stash.copy()
        return recipes.map { recipe ->
            calcRecipe(recipe, stash_)
        }.also {
            log(Yaml.pretty(it))
        }
    }

    private fun calcRecipe(recipe: Recipe, stash: Stash): RecipeResult {

        val stashFlavors = stash.flavorStashes.map { it.flavor }

        val missingFlavors = recipe.flavours
            .map { it.flavor }
            .filterNot { stashFlavors.contains(it) }

//        TODO consider empty stashes
        if (missingFlavors.isNotEmpty()) {
            return RecipeResult(recipe.name, missingFlavors = missingFlavors, remainingStash = stash)
        }

        val maxFlavorStahshes = recipe.flavours
            .map { it to stash.findFlavorStash(it.flavor) }
            .map {
                val amount = (it.second!!.ml * 10 / it.first.gramsPer10ml).round(2)
                FlavorStash(it.first.flavor, amount)
            }
            .sortedBy { it.ml }

        val amountMixableMl = maxFlavorStahshes.minOf { it.ml }
        return RecipeResult(
            recipe.name,
            amountMixableMl,
            (amountMixableMl * recipe.mixingPct).round(2),
            mixableAmountPerFlavor = maxFlavorStahshes,
            remainingStash = stash - maxFlavorStahshes,
        )
    }
}