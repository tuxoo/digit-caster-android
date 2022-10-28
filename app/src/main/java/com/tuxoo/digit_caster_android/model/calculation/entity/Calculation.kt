package com.tuxoo.digit_caster_android.model.calculation.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Calculation(
    var previousNum: String = "",
    var currentNum: String = "",
    var operation: String = "",
) : Parcelable {

    fun toCalculationWithResult(result: String): CalculationWithResult =
        CalculationWithResult(
            previousNum = previousNum,
            currentNum = currentNum,
            operation = operation,
            result = result,
        )
}