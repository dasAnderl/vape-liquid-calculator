package com.dasanderl.vape.liquid

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class Stash(val flavorAmounts: List<FlavorAmountMl>) {
    companion object {
        fun get(): Stash =
            "/stash.yml"
                .let { Stash::class.java.getResource(it).file }
                .let { File(it).readText() }
                .let { Yaml.default.decodeFromString(serializer(), it) }
    }

    fun findFlavorStash(flavor: Flavor) = flavorAmounts.find { it.flavor == flavor }

    /**
     * substracts given flavor amounts from stash
     */
    operator fun minus(flavorAmountsToDeduct: List<FlavorAmountMl>): Stash {

        return this.flavorAmounts
            .map { flavorInStash -> flavorInStash to flavorAmountsToDeduct.find { it.flavor == flavorInStash.flavor } }
            .map { (flavorInStash, flavorToSubstract) -> flavorInStash - flavorToSubstract }
            .let { Stash(it) }
    }
}