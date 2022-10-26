package com.tuxoo.digit_caster_android.model.calculation

import com.tuxoo.digit_caster_android.model.calculation.entity.HistoryItem
import javax.inject.Inject
import javax.inject.Singleton

typealias HistoryListener = (history: List<HistoryItem>) -> Unit

@Singleton
class HistoryService @Inject constructor(){

    private var history = mutableListOf<HistoryItem>()
    private val listeners = mutableSetOf<HistoryListener>()

    init {
        history = (1..5).map {
            HistoryItem(
                operation = "+"
            )
        }.toMutableList()
    }

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