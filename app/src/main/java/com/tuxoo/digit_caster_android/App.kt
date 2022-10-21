package com.tuxoo.digit_caster_android

import android.app.Application
import com.tuxoo.digit_caster_android.model.CalculationService

class App : Application() {

    val calculationService = CalculationService()
}