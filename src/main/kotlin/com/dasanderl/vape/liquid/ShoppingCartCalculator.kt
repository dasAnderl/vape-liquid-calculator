package com.dasanderl.vape.liquid

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import kotlin.math.ceil

object ShoppingCartCalculator {

    @JvmStatic
    fun main(args: Array<String>) {
            get(Stash.get(),
                RecipeAmount("Boss reserve", 400.0),
                RecipeAmount("five pawns queenside spot on", 330.0),
                RecipeAmount("12Monkeys - Mangabeys", 105.0))
    }

    fun get(stash: Stash = Stash.get(), vararg recipeAmounts: RecipeAmount): ShoppingCart {

        val flavorForRecipes = recipeAmounts
            .map { recipeAmount -> Recipe.all().find { it.name == recipeAmount.recipeName } to recipeAmount.amountLiquid }
            .map { FlavorsForRecipe.get(it.first!!, it.second) }

        val neededFlavorsMinusStash = flavorForRecipes
            .flatMap { it.flavors }
            .groupBy { it.flavor }
            .mapValues { it.value.sumByDouble { it.ml } }
            .map { FlavorStash(it.key, it.value ) }
            .let { Stash(it) - stash.flavorStashes }

        return neededFlavorsMinusStash
            .flavorStashes
            .filter { it.ml > 0 }
            .map { FlavorStash(it.flavor, it.ml.round(2)) }
            .let { ShoppingCart(recipeAmounts.toList(), it) }
            .also { log(Yaml.pretty(it)) }
    }

    fun get(amountLiquid: Ml, stash: Stash = Stash.get(), vararg recipeNames: RecipeName): ShoppingCart {
        return recipeNames.map { RecipeAmount(it, amountLiquid) }
            .let { get(stash, *it.toTypedArray()) }
    }
}

@Serializable
data class RecipeAmount(val recipeName: RecipeName, val amountLiquid: Ml)

@Serializable
data class ShoppingCart(val recipeAmounts: List<RecipeAmount>,
                        val flavorsToBuy: List<FlavorStash>, val pricePer10Ml: Double = 3.5) {
    var priceToBuy: Int = 0
    var price: Int = 0
    var pricePer10ml: Double = 0.0
    init {

        priceToBuy = flavorsToBuy
            .map { ceil(it.ml / 10.0).toInt() * pricePer10Ml }
            .sum().toInt()

        price = flavorsToBuy
            .sumByDouble { it.ml / 10 * pricePer10Ml }
            .let{ ceil(it).toInt()}

        pricePer10ml = recipeAmounts.sumByDouble { it.amountLiquid }
            .let { it / 10 }
            .let { price / it }
            .let { it.round(2) }
    }
}