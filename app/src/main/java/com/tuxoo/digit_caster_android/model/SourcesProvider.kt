package com.tuxoo.digit_caster_android.model

import com.tuxoo.digit_caster_android.model.calculation.CalculationSource

interface SourcesProvider {

    fun getCalculationSource(): CalculationSource
}