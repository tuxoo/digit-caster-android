package com.tuxoo.digit_caster_android.model.history.room

import com.tuxoo.digit_caster_android.model.history.HistoryRepository
import com.tuxoo.digit_caster_android.model.history.entity.History
import com.tuxoo.digit_caster_android.model.history.room.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomHistoryRepository(
    private val historyDao: HistoryDao
) : HistoryRepository {

    override suspend fun getAll(): Flow<List<History>> =
        historyDao.findAll().map {
            it.map { entity ->
                entity.toHistory()
            }
        }

    override suspend fun add(history: History) {
        historyDao.save(
            HistoryEntity(
                id = 10,
                operation = "",
                firstNum = "",
                secondNum = "",
                result = "",
            )
        )
    }
}