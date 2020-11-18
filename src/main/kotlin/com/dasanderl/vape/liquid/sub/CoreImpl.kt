package com.dasanderl.vape.liquid.sub

import com.dasanderl.vape.liquid.core.DomainObject
import com.dasanderl.vape.liquid.core.ICore

class CoreImpl : ICore {

    override fun getDomainObject() = DomainObject()
}