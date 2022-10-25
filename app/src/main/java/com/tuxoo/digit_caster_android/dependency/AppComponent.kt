package com.tuxoo.digit_caster_android.dependency

import com.tuxoo.digit_caster_android.model.calculation.CalculationService
import com.tuxoo.digit_caster_android.screens.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, SourcesModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)

//    val calculationService: CalculationService
}