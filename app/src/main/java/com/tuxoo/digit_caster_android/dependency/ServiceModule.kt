package com.tuxoo.digit_caster_android.dependency

import com.tuxoo.digit_caster_android.model.calculation.CalculationService
import com.tuxoo.digit_caster_android.model.calculation.CalculationSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideCalculationService(
        calculationSource: CalculationSource
    ): CalculationService = CalculationService(calculationSource)
}