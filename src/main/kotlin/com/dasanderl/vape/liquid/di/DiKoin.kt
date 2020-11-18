package com.dasanderl.vape.liquid.di

import com.dasanderl.vape.liquid.di.core.BusinessRule2
import com.dasanderl.vape.liquid.di.core.IBusinessRule2
import com.dasanderl.vape.liquid.di.core.IDep
import com.dasanderl.vape.liquid.di.sub.Dep
import com.dasanderl.vape.liquid.log
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject

val module: Module by lazy {
    module {
        single { Dep() as IDep }
        single { BusinessRule2(get()) as IBusinessRule2 }

    }.also {
        log("starting koin")
        startKoin {
            // your modules
            modules(it)
        }
    }
}

inline fun<reified T : Any> inject_() = module.let {
    inject<T>(T::class.java).value
}