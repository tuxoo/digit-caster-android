package com.tuxoo.digit_caster_android.sources.calculation

import com.tuxoo.digit_caster_android.model.calculation.CalculationSource
import com.tuxoo.digit_caster_android.model.calculation.entity.Calculation
import com.tuxoo.digit_caster_android.sources.base.BaseRetrofitSource
import com.tuxoo.digit_caster_android.sources.base.RetrofitConfig
import com.tuxoo.digit_caster_android.sources.calculation.entity.CalculationRequest

class RetrofitCalculationSource(
    config: RetrofitConfig
) : BaseRetrofitSource(config), CalculationSource {

    private val calculationApi = retrofit.create(CalculationApi::class.java)

    override suspend fun calculate(calculation: Calculation): String =
        calculationApi.calculate(
            CalculationRequest(
                previousNum = calculation.previousNum.toDouble(),
                currentNum = calculation.currentNum.toDouble(),
                operation = calculation.operation,
            )
        )
}