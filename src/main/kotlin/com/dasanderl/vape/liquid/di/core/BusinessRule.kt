package com.dasanderl.vape.liquid.di.core

import com.dasanderl.vape.liquid.di.inject_
import org.koin.java.KoinJavaComponent.inject

class BusinessRule(val dep: IDep = inject_<IDep>())

interface IDep



class BusinessRule2(val dep: IDep): IBusinessRule2

interface IBusinessRule2