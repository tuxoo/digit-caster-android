package com.tuxoo.digit_caster_android.model.calculation

import com.tuxoo.digit_caster_android.model.calculation.entity.Calculation

interface CalculationSource {

    suspend fun calculate(calculation: Calculation): String
}
