package com.dasanderl.vape.liquid

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.*

@Serializable
data class RecipeResult(
    val name: String,
    val amountMixableMl: Ml = 0.0,
    val amountMixableLiquidMl: Ml = 0.0,
    val missingFlavors: List<Flavor> = emptyList(),
    val mixableAmountPerFlavor: List<FlavorStash> = emptyList(),
    val remainingStash: Stash,
) {
    fun mixable() = missingFlavors.isEmpty()

    fun stashReduction(stash: Stash, recipe: Recipe): List<FlavorStash> {

        val multiplier = amountMixableMl / 10
        return recipe
            .flavours
            .map { FlavorStash(it.flavor, it.gramsPer10ml * multiplier) }
    }
}

@Serializable
data class Recipe(
    val name: RecipeName,
    val url: String,
    val mixingPct: Percent,
    val flavours: List<FlavorAmount>,
    val desiredAmountMl: Ml = 100.0
) {
    init {
        flavours.sumByDouble { it.gramsPer10ml }
            .also {
                if (it > 13) throw Exception("incorrect flavor grams total $it in 10ml ?")
            }
    }

    companion object {
        fun all(): List<Recipe> =
            "/recipes.json"
                .let { Recipe::class.java.getResource(it).file }
                .let { File(it).readText() }
                .let { Json.decodeFromString<List<Recipe>>(it) }

        fun countFlavorOccurrences(recipes: List<Recipe> = all()): SortedMap<String, List<String>> {
            return recipes
                .flatMap { it.flavours }
                .map { it.flavor }
                .toSet()
                .groupBy ({ it }, { flavor -> recipes.filter { it.flavours.any { it.flavor == flavor } }})
                .mapValues { it.value.flatten().map { it.name } }
                .mapKeys { "${it.value.size} - ${it.key}" }
                .toSortedMap()
                .also {
                    Json.pretty(it.toMap()).also { log(it) }
                }
        }

        fun byName(name: RecipeName) = all().find { it.name == name }!!
    }
}