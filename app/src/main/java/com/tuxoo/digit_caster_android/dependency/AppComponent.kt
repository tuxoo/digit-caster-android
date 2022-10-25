package com.tuxoo.digit_caster_android.dependency

import com.tuxoo.digit_caster_android.model.calculation.CalculationService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, SourcesModule::class])
interface AppComponent {

    val calculationService: CalculationService
}