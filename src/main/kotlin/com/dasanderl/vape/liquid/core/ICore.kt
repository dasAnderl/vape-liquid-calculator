package com.dasanderl.vape.liquid.core

import com.dasanderl.vape.liquid.di.get

interface ICore {

    fun getDomainObject(): DomainObject
}

data class DomainObject(val i: Int = 0)

class BusinessRule(val core: ICore = get()) {

    fun doSth(): DomainObject {
        return core.getDomainObject()
    }
}