package com.dasanderl.vape.liquid

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.encodeToString

inline fun <reified T> Yaml.Companion.pretty(value: T) = Yaml.default.encodeToString(value)

fun log(msg: String) = print(msg)

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}

typealias Ml = Double
typealias GramsPer10Ml = Double
typealias RecipeName = String
typealias Flavor = String
typealias Percent = Double