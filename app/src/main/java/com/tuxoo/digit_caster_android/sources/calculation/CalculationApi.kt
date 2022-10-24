package com.tuxoo.digit_caster_android.sources.calculation

import com.tuxoo.digit_caster_android.sources.calculation.entity.CalculationRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface CalculationApi {

    @POST("/api/v1/calculation")
    suspend fun calculate(@Body request: CalculationRequest): String
}