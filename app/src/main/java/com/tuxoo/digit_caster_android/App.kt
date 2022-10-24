package com.tuxoo.digit_caster_android

import android.app.Application
import com.tuxoo.digit_caster_android.model.calculation.CalculationService
import com.tuxoo.digit_caster_android.sources.SourceProviderHolder

class App : Application() {

    private val sourcesProvider = SourceProviderHolder.sourcesProvider
    private val calculationSource = sourcesProvider.getCalculationSource()
    val calculationService = CalculationService(calculationSource)
}