package com.tuxoo.digit_caster_android.screens.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuxoo.digit_caster_android.model.history.HistoryRepository
import com.tuxoo.digit_caster_android.model.history.entity.History
import kotlinx.coroutines.launch

class HistoryViewModel(
    historyRepository: HistoryRepository
) : ViewModel() {

    private val _history = MutableLiveData<List<History>>()
    var history: LiveData<List<History>> = _history

    init {
        viewModelScope.launch {
            historyRepository.getAll().collect() {
                it.forEach {
                    Log.d("E", it.toString())
                }
                _history.value = it
            }
        }
    }
}