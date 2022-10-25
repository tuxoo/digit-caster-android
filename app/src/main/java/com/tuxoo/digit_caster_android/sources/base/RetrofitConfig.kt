package com.tuxoo.digit_caster_android.sources.base

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

class RetrofitConfig(
    val retrofit: Retrofit,
    val moshi: Moshi
) {
}