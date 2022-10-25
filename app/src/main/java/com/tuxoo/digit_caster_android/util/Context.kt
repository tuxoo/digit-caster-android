package com.tuxoo.digit_caster_android.util

import android.content.Context
import com.tuxoo.digit_caster_android.App
import com.tuxoo.digit_caster_android.dependency.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }