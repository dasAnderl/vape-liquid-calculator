package com.dasanderl.vape.liquid.core

import com.dasanderl.vape.liquid.di.set
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BusinessRuleTest : StringSpec({

    "doSth" {

        BusinessRule().doSth() shouldBe DomainObject()
    }

    "doSth mocked" {

        set<ICore>(CoreTestImpl())

        BusinessRule().doSth() shouldBe DomainObject(2)
    }

    "doSth mocked constructor injection" {

        BusinessRule(CoreTestImpl()).doSth() shouldBe DomainObject(2)
    }
})

internal class CoreTestImpl: ICore {
    override fun getDomainObject(): DomainObject = DomainObject(2)
}
