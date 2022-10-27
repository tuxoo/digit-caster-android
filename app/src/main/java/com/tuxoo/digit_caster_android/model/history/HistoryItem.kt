package com.tuxoo.digit_caster_android.model.history

import com.tuxoo.digit_caster_android.model.calculation.entity.Calculation

data class HistoryItem(
    val operation: String,
    val firstNum: String,
    val secondNum: String,
    val result: String,
) {

    fun toCalculation(): Calculation = Calculation(
        previousNum = this.firstNum,
        currentNum = this.secondNum,
        operation = this.operation
    )
}