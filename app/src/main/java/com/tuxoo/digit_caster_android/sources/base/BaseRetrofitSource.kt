package com.tuxoo.digit_caster_android.sources.base

import retrofit2.Retrofit

open class BaseRetrofitSource(
    retrofitConfig: RetrofitConfig
) {

    val retrofit: Retrofit = retrofitConfig.retrofit
}