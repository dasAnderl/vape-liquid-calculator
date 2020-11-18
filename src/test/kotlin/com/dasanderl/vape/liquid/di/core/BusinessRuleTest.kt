package com.dasanderl.vape.liquid.di.core

import com.dasanderl.vape.liquid.di.inject_
import com.dasanderl.vape.liquid.di.sub.Dep
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeSameInstanceAs

class BusinessRuleTest : StringSpec({

    "BusinessRule" {
        BusinessRule().dep shouldNotBe null
    }

    "BusinessRule 2" {
        inject_<IBusinessRule2>() shouldNotBe null
    }
})
