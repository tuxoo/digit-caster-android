package com.tuxoo.digit_caster_android.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tuxoo.digit_caster_android.model.history.room.HistoryDao
import com.tuxoo.digit_caster_android.model.history.room.entity.HistoryEntity

@Database(
    version = 1,
    entities = [
        HistoryEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getHistoryDao(): HistoryDao
}