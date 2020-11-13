package com.dasanderl.vape.liquid

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

internal fun Stash.Companion.test(): Stash = "/stash-test.json"
    .let { Stash::class.java.getResource(it).file }
    .let { File(it).readText() }
    .let { Json.decodeFromString<Stash>(it) }

internal fun Recipe.Companion.test(): List<Recipe> = "/recipes-test.json"
    .let { Recipe::class.java.getResource(it).file }
    .let { File(it).readText() }
    .let { Json.decodeFromString<List<Recipe>>(it) }