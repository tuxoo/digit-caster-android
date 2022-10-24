package com.tuxoo.digit_caster_android.sources.base

import com.tuxoo.digit_caster_android.model.SourcesProvider
import com.tuxoo.digit_caster_android.model.calculation.CalculationSource
import com.tuxoo.digit_caster_android.sources.calculation.RetrofitCalculationSource

class RetrofitSourcesProvider(
    private val config: RetrofitConfig
) : SourcesProvider {

    override fun getCalculationSource(): CalculationSource =
        RetrofitCalculationSource(config)
}