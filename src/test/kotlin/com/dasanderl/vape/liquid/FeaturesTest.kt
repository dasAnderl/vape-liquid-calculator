package com.dasanderl.vape.liquid

import com.charleskorn.kaml.Yaml
import io.kotest.core.spec.style.StringSpec

class FeaturesTest : StringSpec({

    "calc cart" {

//        Features.calcCartForRecipes(
//            Stash.get(),
//            RecipeAmount("Boss reserve", 500.0),
//            RecipeAmount("five pawns queenside spot on", 500.0),
//            RecipeAmount("12Monkeys - Mangabeys", 100.0),
//        )
    }

    "reduce stash by recipes" {

        listOf(
            RecipeAmount("Boss reserve", 400.0),
            RecipeAmount("five pawns queenside spot on", 115.0),
        )
            .let {
                Features.reduceStashByRecipeAmounts(it)
                    .also {
                        println(Yaml.pretty(it))
                    }
            }
    }

})
