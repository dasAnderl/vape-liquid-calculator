package com.dasanderl.vape.liquid

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import java.io.File
import java.util.*

@Serializable
data class RecipeResult(
    val name: String,
    val amountMixableMl: Ml = 0.0,
    val amountMixableLiquidMl: Ml = 0.0,
    val missingFlavors: List<Flavor> = emptyList(),
    val mixableAmountPerFlavor: List<FlavorAmountMl> = emptyList(),
    val remainingStash: Stash,
)

@Serializable
data class Recipe(
    val name: RecipeName,
    val url: String,
    val mixingPct: Percent,
    val flavours: List<FlavorGramsPer10MlAroma>
) {
    init {
        flavours.sumByDouble { it.gramsPer10ml }
            .also {
                if (it > 13) throw Exception("incorrect flavor grams total $it in 10ml ?")
                if (it < 0.95) throw Exception("incorrect flavor grams total $it in 10ml ?")
            }
    }

    companion object {
        fun all(): List<Recipe> =
            "/recipes.yaml"
                .let { Recipe::class.java.getResource(it).file }
                .let { File(it).readText() }
                .let { Yaml.default.decodeFromString(it) }

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
                    Yaml.pretty(it.toMap()).also { log(it) }
                }
        }

        fun byName(name: RecipeName) = all().find { it.name == name }!!
    }
}

@Serializable
data class RecipeAmount(val recipeName: RecipeName, val amountLiquid: Ml)