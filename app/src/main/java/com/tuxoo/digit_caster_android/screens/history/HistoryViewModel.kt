package com.tuxoo.digit_caster_android.screens.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuxoo.digit_caster_android.model.history.HistoryItem
import com.tuxoo.digit_caster_android.model.history.HistoryListener
import com.tuxoo.digit_caster_android.model.history.HistoryService
import kotlinx.coroutines.launch

class HistoryViewModel(
    historyService: HistoryService
) : ViewModel() {

    private val _history = MutableLiveData<List<HistoryItem>>()
    val history: LiveData<List<HistoryItem>> = _history

    init {
        viewModelScope.launch {
            historyService.listenHistory()
                .collect {
                    _history.value = it
                }
        }
        _history.value = historyService.getHistory()
    }
}