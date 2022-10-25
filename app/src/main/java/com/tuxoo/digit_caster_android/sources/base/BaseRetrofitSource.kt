package com.tuxoo.digit_caster_android.sources.base

import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

open class BaseRetrofitSource(
    config: RetrofitConfig
) {

    val retrofit: Retrofit = config.retrofit

    private val contentType = "application/json; charset=utf-8".toMediaType()
}