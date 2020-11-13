package com.dasanderl.vape.liquid

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class Stash(val flavorStashes: List<FlavorStash>) {
    companion object {
        fun get(): Stash =
            "/stash.json"
                .let { Stash::class.java.getResource(it).file }
                .let { File(it).readText() }
                .let { Json.decodeFromString<Stash>(it) }
    }

    fun findFlavorStash(flavor: Flavor) = flavorStashes.find { it.flavor == flavor }

    operator fun minus(flavorStashesMinus: List<FlavorStash>): Stash {

        return this.flavorStashes
            .map { flavorInStash -> flavorInStash to flavorStashesMinus.find { it.flavor == flavorInStash.flavor } }
            .map { (flavorInStash, flavorToSubstract) -> flavorInStash - flavorToSubstract }
            .let { Stash(it) }
    }
}