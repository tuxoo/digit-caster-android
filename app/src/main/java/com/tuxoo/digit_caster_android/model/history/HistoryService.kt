package com.tuxoo.digit_caster_android.model.history

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

    fun addListener(listener: HistoryListener) {
        listeners.add(listener)
        listener.invoke(history)
    }

    fun removeListener(listener: HistoryListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(history) }
    }
}