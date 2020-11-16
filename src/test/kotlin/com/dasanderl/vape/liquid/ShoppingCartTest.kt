package com.dasanderl.vape.liquid

import com.charleskorn.kaml.Yaml
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ShoppingCartTest : StringSpec({

    """given an amounts and recipe names, i want to know how many ml of each aroma has to be bought
       the stash has to be considered
    """ {

        ShoppingCartCalculator
            .get(1000.0, Stash.test(), "Boss reserve", "five pawns queenside spot on")
            .let {
                Yaml.pretty(it)
            }.also {
                it shouldBe """recipeAmounts:
- recipeName: "Boss reserve"
  amountLiquid: 1000.0
- recipeName: "five pawns queenside spot on"
  amountLiquid: 1000.0
flavorsToBuy:
- flavor: "Acetyl Pyrazine 5% (TPA)"
  ml: 0.49
- flavor: "Banana Cream (TPA)"
  ml: 5.3
- flavor: "Milk and Honey (Flavorah)"
  ml: 5.93
- flavor: "Blood Orange (Natural) (FW)"
  ml: 41.61
- flavor: "French Vanilla (CAP)"
  ml: 30.1
- flavor: "Orange Cream (TPA)"
  ml: 29.29
- flavor: "Vanilla Custard (CAP)"
  ml: 99.99
- flavor: "Vanillin 10% (PG) (TPA)"
  ml: 2.02
pricePer10Ml: 3.5
priceToBuy: 91
price: 76
pricePer10ml: 0.38"""
            }
    }
})

