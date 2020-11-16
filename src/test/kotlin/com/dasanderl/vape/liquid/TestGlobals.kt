package com.dasanderl.vape.liquid

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.decodeFromString
import java.io.File

internal fun Stash.Companion.test(): Stash = "/stash-test.yaml"
    .let { Stash::class.java.getResource(it).file }
    .let { File(it).readText() }
    .let { Yaml.default.decodeFromString(it) }

internal fun Recipe.Companion.test(): List<Recipe> = "/recipes-test.yaml"
    .let { Recipe::class.java.getResource(it).file }
    .let { File(it).readText() }
    .let { Yaml.default.decodeFromString(it) }