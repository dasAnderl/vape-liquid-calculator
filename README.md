Vape liquid calculator
======================

given recipes.yaml and a flavors in stash.yaml, this little app
calculates what you have to buy in aromas in order to produce
given recipes and amounts considering your given stash.

Usage
=====

```
ShoppingCartCalculator.get(
                Stash.get(),
                RecipeAmount("Boss reserve", 400.0),
                RecipeAmount("five pawns queenside spot on", 330.0),
                RecipeAmount("12Monkeys - Mangabeys", 105.0))
```
               
Result:

```
{
    "recipeAmounts": [
        {
            "recipeName": "Boss reserve",
            "amountLiquid": 400.0
        },
        {
            "recipeName": "five pawns queenside spot on",
            "amountLiquid": 330.0
        },
        {
            "recipeName": "12Monkeys - Mangabeys",
            "amountLiquid": 105.0
        }
    ],
    "flavorsToBuy": [
        {
            "flavor": "Acetyl Pyrazine 5% (TPA)",
            "ml": 4.19
        },
        {
            "flavor": "Banana Cream (TPA)",
            "ml": 6.12
        },
        {
            "flavor": "Biscuit (INAWERA)",
            "ml": 1.06
        },
        {
            "flavor": "Milk and Honey (Flavorah)",
            "ml": 6.37
        },
        {
            "flavor": "Super Sweet (CAP)",
            "ml": 1.08
        },
        {
            "flavor": "Blood Orange (Natural) (FW)",
            "ml": 13.73
        },
        {
            "flavor": "French Vanilla (CAP)",
            "ml": 9.93
        },
        {
            "flavor": "Orange Cream (TPA)",
            "ml": 9.67
        },
        {
            "flavor": "Vanilla Custard (CAP)",
            "ml": 33.0
        },
        {
            "flavor": "Vanillin 10% (PG) (TPA)",
            "ml": 0.67
        },
        {
            "flavor": "Mango (TPA)",
            "ml": 7.35
        },
        {
            "flavor": "Pineapple (TPA)",
            "ml": 9.58
        },
        {
            "flavor": "Sweet Guava (CAP)",
            "ml": 4.33
        }
    ],
    "priceToBuy": 59,
    "price": 38,
    "pricePer10ml": 0.46
}
```                