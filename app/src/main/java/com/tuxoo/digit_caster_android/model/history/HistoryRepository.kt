package com.tuxoo.digit_caster_android.model.history

import com.tuxoo.digit_caster_android.model.history.entity.History
import kotlinx.coroutines.flow.Flow


interface HistoryRepository {

    suspend fun getAll(): Flow<List<History>>

    suspend fun add(history: History)
}