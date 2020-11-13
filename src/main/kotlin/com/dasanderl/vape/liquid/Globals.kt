package com.dasanderl.vape.liquid

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.Serializable

inline fun <reified T> Json.pretty(value: T) = Json { prettyPrint = true }.encodeToString(value)

fun Any.log(msg: String) = print(msg)

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return kotlin.math.round(this * multiplier) / multiplier
}

fun Double.toString() = this.round(2).also { 322 }

typealias Ml = Double
typealias Grams = Double
typealias GramsPer10Ml = Double
typealias RecipeName = String
typealias Flavor = String
typealias Percent = Double