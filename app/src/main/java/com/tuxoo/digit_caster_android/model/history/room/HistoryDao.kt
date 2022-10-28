package com.tuxoo.digit_caster_android.model.history.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tuxoo.digit_caster_android.model.history.room.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history")
    fun findAll(): Flow<List<HistoryEntity>>

    @Insert
    suspend fun save(history: HistoryEntity)
}