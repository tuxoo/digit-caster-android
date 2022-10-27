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
            firstNum = "88",
            secondNum = "12",
            result = "100",
        ),
        HistoryItem(
            operation = "-",
            firstNum = "60",
            secondNum = "12",
            result = "48",
        ),
        HistoryItem(
            operation = "*",
            firstNum = "15",
            secondNum = "1",
            result = "15",
        ),
        HistoryItem(
            operation = "-",
            firstNum = "60",
            secondNum = "12",
            result = "48",
        ),
        HistoryItem(
            operation = "/",
            firstNum = "44",
            secondNum = "11",
            result = "4",
        ),
    )
    private val listeners = mutableSetOf<HistoryListener>()

    fun getHistory(): List<HistoryItem> = history

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