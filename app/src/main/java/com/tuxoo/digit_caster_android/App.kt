package com.tuxoo.digit_caster_android

import android.app.Application
import com.tuxoo.digit_caster_android.dependency.AppComponent
import com.tuxoo.digit_caster_android.dependency.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .applicationContext(applicationContext)
            .build()
    }
}