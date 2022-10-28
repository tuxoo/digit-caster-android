package com.tuxoo.digit_caster_android.model.history.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tuxoo.digit_caster_android.model.history.entity.History

@Entity(
    tableName = "history"
)
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val operation: String,
    @ColumnInfo(name = "first_number") val firstNum: String,
    @ColumnInfo(name = "second_number") val secondNum: String,
    val result: String,
) {
    fun toHistory(): History = History(
        firstNum = this.firstNum,
        secondNum = this.secondNum,
        operation = this.operation,
        result = this.result
    )
}