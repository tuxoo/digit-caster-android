package com.tuxoo.digit_caster_android.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuxoo.digit_caster_android.model.calculation.HistoryListener
import com.tuxoo.digit_caster_android.model.calculation.HistoryService
import com.tuxoo.digit_caster_android.model.calculation.entity.HistoryItem

class HistoryViewModel(
    historyService: HistoryService
) : ViewModel() {

    private val _history = MutableLiveData<List<HistoryItem>>()
    val history: LiveData<List<HistoryItem>> = _history

    private val listener: HistoryListener = {
        _history.value = it
    }

    init {
        historyService.addListener(listener)
    }
}