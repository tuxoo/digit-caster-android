package com.tuxoo.digit_caster_android.sources.calculation.entity

data class CalculationRequest(
    val previousNum: Double,
    val currentNum: Double,
    val operation: String,
)
