package com.tuxoo.digit_caster_android.model.history

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

typealias HistoryListener = (history: List<HistoryItem>) -> Unit

@Singleton
class HistoryService @Inject constructor() {

    private var history = listOf(
        HistoryItem(
            operation = "+",
            topNum = "88",
            bottomNum = "12",
            result = "100",
        ),
        HistoryItem(
            operation = "-",
            topNum = "60",
            bottomNum = "12",
            result = "48",
        ),
        HistoryItem(
            operation = "*",
            topNum = "15",
            bottomNum = "1",
            result = "15",
        ),
        HistoryItem(
            operation = "/",
            topNum = "44",
            bottomNum = "11",
            result = "4",
        )
    )
    private val listeners = mutableSetOf<HistoryListener>()

    fun getHistory() : List<HistoryItem> = history

    private fun notifyChanges() {
        listeners.forEach { it.invoke(history) }
    }

    fun listenHistory(): Flow<List<HistoryItem>> = callbackFlow {
        val listener: HistoryListener = {
            trySend(it)
        }
        listeners.add(listener)

        awaitClose {
            listeners.remove(listener)
        }
    }
}