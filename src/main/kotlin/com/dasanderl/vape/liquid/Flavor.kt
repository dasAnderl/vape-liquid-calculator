package com.dasanderl.vape.liquid

import kotlinx.serialization.Serializable

@Serializable
data class FlavorAmount(val flavor: Flavor, val gramsPer10ml: GramsPer10Ml) {
    init {
        if (gramsPer10ml >= 10) throw Exception("grams incorrect $gramsPer10ml in 10ml ?")
    }
}

@Serializable
data class FlavorStash(val flavor: Flavor, val ml: Ml) {
    operator fun minus(flavorStash: FlavorStash?): FlavorStash {
        if (flavor != flavorStash?.flavor) return this
        return FlavorStash(flavor, (ml - flavorStash.ml).round(2))
    }
}


@Serializable
data class FlavorsForRecipe(
    val recipeName: RecipeName, val liquidAmountMl: Ml, val aromaAmountMl: Ml, val flavors: List<FlavorStash>
) {

    companion object {
        fun get(recipe: Recipe, liquidAmount: Ml): FlavorsForRecipe {
            val totalAromaAmountMl = liquidAmount * recipe.mixingPct / 100
            val usedFlavors = recipe
                .flavours
                .map {
                    val amountPerMlAroma = it.gramsPer10ml / 10.0
                    val flavorAmount = totalAromaAmountMl * amountPerMlAroma
                    FlavorStash(it.flavor, flavorAmount.round(2))
                }
            return FlavorsForRecipe(recipe.name, liquidAmount, totalAromaAmountMl, usedFlavors)
        }
    }
}

