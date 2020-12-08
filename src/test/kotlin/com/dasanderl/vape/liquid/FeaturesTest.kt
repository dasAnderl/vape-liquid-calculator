package com.dasanderl.vape.liquid

import io.kotest.core.spec.style.StringSpec

class FeaturesTest : StringSpec({

    "calc cart" {

        Features.calcCartForRecipes(
            Stash.get(),
            RecipeAmount("Boss reserve", 500.0),
            RecipeAmount("five pawns queenside spot on", 500.0),
            RecipeAmount("12Monkeys - Mangabeys", 100.0),
        )

    }

})
