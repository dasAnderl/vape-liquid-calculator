package com.dasanderl.vape.liquid

import kotlinx.serialization.Serializable

@Serializable
data class FlavorGramsPer10MlAroma(val flavor: Flavor, val gramsPer10ml: GramsPer10Ml) {
    init {
        if (gramsPer10ml >= 10) throw Exception("grams incorrect $gramsPer10ml in 10ml ?")
    }
}

@Serializable
data class FlavorAmountMl(val flavor: Flavor, val ml: Ml) {
    operator fun minus(flavorAmounts: FlavorAmountMl?): FlavorAmountMl {
        if (flavor != flavorAmounts?.flavor) return this
        return FlavorAmountMl(flavor, (ml - flavorAmounts.ml).round(2))
    }
}


@Serializable
data class FlavorsForRecipe(
    val recipeName: RecipeName, val liquidAmountMl: Ml, val aromaAmountMl: Ml, val flavors: List<FlavorAmountMl>
) {

    companion object {
        fun get(recipe: Recipe, liquidAmount: Ml): FlavorsForRecipe {
            val totalAromaAmountMl = liquidAmount * recipe.mixingPct / 100
            val usedFlavors = recipe
                .flavours
                .map {
                    val amountPerMlAroma = it.gramsPer10ml / 10.0
                    val flavorAmount = totalAromaAmountMl * amountPerMlAroma
                    FlavorAmountMl(it.flavor, flavorAmount.round(2))
                }
            return FlavorsForRecipe(recipe.name, liquidAmount, totalAromaAmountMl, usedFlavors)
        }

        fun get(recipeAmounts: List<RecipeAmount>, stash: Stash = Stash.get()): Stash =
            recipeAmounts
                .map { Recipe.byName(it.recipeName) to it.amountLiquid }
                .map { get(it.first, it.second) }
                .flatMap { it.flavors}
                .let {
                    stash - it
                }
    }
}

