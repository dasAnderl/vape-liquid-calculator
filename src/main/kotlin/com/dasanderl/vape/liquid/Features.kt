package com.dasanderl.vape.liquid

import java.util.*

object Features {

    /**
     * calculate how much can be mixed of given recipes. the stash is reduction is printed
     */
    fun calcRecipesAgainstStash(stash: Stash = Stash.get(), recipes: List<Recipe>): List<RecipeResult> =
        Calculator.calc(stash, recipes)

    /**
     * which flavor amounts are needed to produce the given amount of given recipe
     */
    fun get(recipe: Recipe, liquidAmount: Ml): FlavorsForRecipe =
        FlavorsForRecipe.get(recipe, liquidAmount)

    /**
     * counts flavor occurrences in given recipes
     */
    fun countFlavorOccurrences(recipes: List<Recipe> = Recipe.all()): SortedMap<String, List<String>> =
        Recipe.countFlavorOccurrences(recipes)

    /**
     * calcs what must be bought to mix the given recipe amounts, considering the stash
     */
    fun calcCartForRecipes(stash: Stash = Stash.get(), vararg recipeAmounts: RecipeAmount): ShoppingCart =
        ShoppingCartCalculator.get(stash, *recipeAmounts)

    /**
     * takes the given stash and reduces it by the given recipes amount
     */
    fun reduceStashByRecipeAmounts(recipeAmounts: List<RecipeAmount>, stash: Stash = Stash.get()): Stash =
        Calculator.reduceStashByRecipeAmounts(recipeAmounts, stash)
}