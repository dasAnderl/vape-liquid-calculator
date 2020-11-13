package com.dasanderl.vape.liquid

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class StashTest : StringSpec({

    val stash = Stash.test()

    "get stash" {
        stash.flavorStashes[0] shouldNotBe null
    }

    "stash minus itself" {
        (stash - stash.flavorStashes)
            .also {
                it.flavorStashes.map { it.ml }.sum() shouldBe 0
            }
    }

    "stash minus empty" {
        (stash - emptyList())
            .also {
                it shouldBe stash
            }
    }

    "stash minus one flavor" {
        (stash - listOf(FlavorStash("Milk and Honey (Flavorah)", 8.5)))
            .also {
                it.findFlavorStash("Milk and Honey (Flavorah)")!!.ml shouldBe 1.5
                it.flavorStashes.sumByDouble { it.ml } shouldBe 41.5
            }
    }
})
