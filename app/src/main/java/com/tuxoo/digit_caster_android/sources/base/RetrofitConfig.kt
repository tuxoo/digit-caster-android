package com.tuxoo.digit_caster_android.sources.base

import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitConfig @Inject constructor(
    val retrofit: Retrofit,
) {
}