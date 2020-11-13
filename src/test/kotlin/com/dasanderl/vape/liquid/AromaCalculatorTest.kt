package com.dasanderl.vape.liquid

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.json.Json

class AromaCalculatorTest : StringSpec({

 val stash = Stash.test()
 val recipes = Recipe.test()
 val recipe = recipes[0]

 "stashreduction" {

  Calculator.calc(stash, recipes)[0]
   .stashReduction(stash, recipe)
   .also { Json.pretty(it) shouldBe """[
    {
        "flavor": "Acetyl Pyrazine 5% (TPA)",
        "ml": 6.582250000000001
    },
    {
        "flavor": "Banana Cream (TPA)",
        "ml": 9.605
    },
    {
        "flavor": "Biscuit (INAWERA)",
        "ml": 1.66675
    },
    {
        "flavor": "Milk and Honey (Flavorah)",
        "ml": 10.0005
    },
    {
        "flavor": "Super Sweet (CAP)",
        "ml": 1.695
    }
]""" }
 }

 "calc" {

  Calculator.calc(stash, recipes)
   .also {
    Json.pretty(it) shouldBe
            """[
    {
        "name": "Boss reserve",
        "amountMixableMl": 28.25,
        "amountMixableLiquidMl": 127.12,
        "mixableAmountPerFlavor": [
            {
                "first": "Milk and Honey (Flavorah)",
                "second": 28.25
            },
            {
                "first": "Banana Cream (TPA)",
                "second": 29.41
            },
            {
                "first": "Acetyl Pyrazine 5% (TPA)",
                "second": 42.92
            },
            {
                "first": "Super Sweet (CAP)",
                "second": 166.67
            },
            {
                "first": "Biscuit (INAWERA)",
                "second": 169.49
            }
        ]
    },
    {
        "name": "five pawns queenside spot on",
        "missingFlavors": [
            "Blood Orange (Natural) (FW)",
            "French Vanilla (CAP)",
            "Orange Cream (TPA)",
            "Vanilla Custard (CAP)",
            "Vanillin 10% (PG) (TPA)"
        ]
    }
]"""
   }
 }
})
