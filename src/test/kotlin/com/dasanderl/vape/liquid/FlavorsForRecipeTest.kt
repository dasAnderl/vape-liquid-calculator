package com.dasanderl.vape.liquid

import com.charleskorn.kaml.Yaml
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class FlavorsForRecipeTest : StringSpec({

    val recipe = Recipe.byName("Boss reserve")

    "get 100 ml" {
        FlavorsForRecipe.get(recipe, 100.0)
            .also {
                it.recipeName shouldBe "Boss reserve"
                it.liquidAmountMl shouldBe 100.0
                it.aromaAmountMl shouldBe 4.5
                it.flavors.sumByDouble { it.ml } shouldBe 4.710000000000001
            }.also {
                log(Yaml.pretty(it))
            }
    }

    "get 200 ml" {

        FlavorsForRecipe.get(recipe, 200.0)
            .also {
                it.recipeName shouldBe "Boss reserve"
                it.liquidAmountMl shouldBe 200.0
                it.aromaAmountMl shouldBe 9.0
                it.flavors.sumByDouble { it.ml } shouldBe 9.420000000000002
            }.also {
                log(Yaml.pretty(it))
            }
    }
})
