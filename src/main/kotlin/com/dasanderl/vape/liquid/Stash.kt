package com.dasanderl.vape.liquid

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class Stash(val flavorStashes: List<FlavorStash>) {
    companion object {
        fun get(): Stash =
            "/stash.yml"
                .let { Stash::class.java.getResource(it).file }
                .let { File(it).readText() }
                .let { Yaml.default.decodeFromString(serializer(), it) }
    }

    fun findFlavorStash(flavor: Flavor) = flavorStashes.find { it.flavor == flavor }

    operator fun minus(flavorStashesMinus: List<FlavorStash>): Stash {

        return this.flavorStashes
            .map { flavorInStash -> flavorInStash to flavorStashesMinus.find { it.flavor == flavorInStash.flavor } }
            .map { (flavorInStash, flavorToSubstract) -> flavorInStash - flavorToSubstract }
            .let { Stash(it) }
    }
}