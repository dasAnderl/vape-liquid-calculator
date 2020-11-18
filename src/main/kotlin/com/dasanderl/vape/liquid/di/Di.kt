package com.dasanderl.vape.liquid.di

import com.dasanderl.vape.liquid.core.ICore
import com.dasanderl.vape.liquid.sub.CoreImpl
import kotlin.reflect.KClass

private var implementations = mapOf<KClass<*>, Any>(
    ICore::class to CoreImpl()
)
fun implementations() = implementations.toMap()

inline fun <reified T : Any> get(): T = implementations()[T::class] as T

@Deprecated("for testing only")
fun set(deps: Map<KClass<*>, Any>) {
    implementations = deps
}

@Deprecated("for testing only")
inline fun <reified T: Any> set(impl: Any) {
    set(mapOf(T::class to impl))
}



