package com.tuxoo.digit_caster_android.model.history.entity

import com.tuxoo.digit_caster_android.model.calculation.entity.Calculation
import com.tuxoo.digit_caster_android.model.history.room.entity.HistoryEntity

data class History(
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

    fun toEntity(): HistoryEntity = HistoryEntity(
        id = 1,
        operation = this.operation,
        firstNum = this.firstNum,
        secondNum = this.secondNum,
        result = this.result
    )
}