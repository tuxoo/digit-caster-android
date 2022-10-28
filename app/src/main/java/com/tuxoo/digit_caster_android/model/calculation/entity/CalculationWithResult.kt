package com.tuxoo.digit_caster_android.model.calculation.entity

data class CalculationWithResult(
    var previousNum: String,
    var currentNum: String,
    var operation: String,
    val result: String
)
