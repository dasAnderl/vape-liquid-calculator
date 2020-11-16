package com.dasanderl.vape.liquid

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe

class RecipeTest : StringSpec({

 "all" {
  Recipe.test().also {
   it.size shouldBeGreaterThan 0
  }
 }

 "countFlavorOccurrence" {
  Recipe.countFlavorOccurrences()
   .also {
    println(it)
   }
 }
})
