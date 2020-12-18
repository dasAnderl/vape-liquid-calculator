package com.dasanderl.vape.liquid

import com.charleskorn.kaml.Yaml
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.lang.Exception

class CalculatorTest : StringSpec({

 val stash = Stash.test()
 val recipes = Recipe.test()

 "reduceStashByRecipeAmounts" {

  listOf(
   RecipeAmount("Boss reserve", 100.0),
//   RecipeAmount("five pawns queenside spot on", 7.0),
  )
   .let {
    Features.reduceStashByRecipeAmounts(it, stash)
   }.also {
    Yaml.pretty(it) shouldBe """flavorAmounts:
- flavor: "Acetyl Pyrazine 5% (TPA)"
  ml: 8.95
- flavor: "Banana Cream (TPA)"
  ml: 8.47
- flavor: "Biscuit (INAWERA)"
  ml: 9.73
- flavor: "Milk and Honey (Flavorah)"
  ml: 8.41
- flavor: "Super Sweet (CAP)"
  ml: 9.73""".trimIndent()
   }
 }

// "reduceStashByRecipeAmounts should throw" {
//
//  listOf(
//   RecipeAmount("Boss reserve", 1000000000.0),
//   RecipeAmount("five pawns queenside spot on", 7.0),
//  )
//   .also {
//    shouldThrow<Exception> {
//     Features.reduceStashByRecipeAmounts(it, stash)
//    }
//   }
// }


 "calc" {

  Calculator.calc(stash, recipes)
   .also {
    Yaml.pretty(it) shouldBe
            """- name: "Boss reserve"
  amountMixableMl: 28.25
  amountMixableLiquidMl: 627.78
  missingFlavors: []
  mixableAmountPerFlavor:
  - flavor: "Milk and Honey (Flavorah)"
    ml: 28.25
  - flavor: "Banana Cream (TPA)"
    ml: 29.41
  - flavor: "Acetyl Pyrazine 5% (TPA)"
    ml: 42.92
  - flavor: "Super Sweet (CAP)"
    ml: 166.67
  - flavor: "Biscuit (INAWERA)"
    ml: 169.49
  remainingStash:
    flavorAmounts:
    - flavor: "Acetyl Pyrazine 5% (TPA)"
      ml: 3.42
    - flavor: "Banana Cream (TPA)"
      ml: 0.39
    - flavor: "Biscuit (INAWERA)"
      ml: 8.33
    - flavor: "Milk and Honey (Flavorah)"
      ml: 0.0
    - flavor: "Super Sweet (CAP)"
      ml: 8.3
- name: "five pawns queenside spot on"
  amountMixableMl: 0.0
  amountMixableLiquidMl: 0.0
  missingFlavors:
  - "Blood Orange (Natural) (FW)"
  - "French Vanilla (CAP)"
  - "Orange Cream (TPA)"
  - "Vanilla Custard (CAP)"
  - "Vanillin 10% (PG) (TPA)"
  mixableAmountPerFlavor: []
  remainingStash:
    flavorAmounts:
    - flavor: "Acetyl Pyrazine 5% (TPA)"
      ml: 3.42
    - flavor: "Banana Cream (TPA)"
      ml: 0.39
    - flavor: "Biscuit (INAWERA)"
      ml: 8.33
    - flavor: "Milk and Honey (Flavorah)"
      ml: 0.0
    - flavor: "Super Sweet (CAP)"
      ml: 8.3"""
   }
 }
})
