package com.tuxoo.digit_caster_android.screens.calculation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuxoo.digit_caster_android.R
import com.tuxoo.digit_caster_android.model.calculation.CalculationService
import com.tuxoo.digit_caster_android.model.calculation.entity.Calculation
import com.tuxoo.digit_caster_android.model.history.HistoryRepository
import com.tuxoo.digit_caster_android.util.MutableLiveEvent
import com.tuxoo.digit_caster_android.util.publishEvent
import com.tuxoo.digit_caster_android.util.share
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CalculationViewModel(
    private val calculationService: CalculationService,
    historyRepository: HistoryRepository,
) : ViewModel() {

    private val _calculation = MutableLiveData<Calculation>()
    val calculation: LiveData<Calculation> = _calculation

    private val _showToastEvent = MutableLiveEvent<Int>()
    val showToastEvent = _showToastEvent.share()

    init {
        viewModelScope.launch {
            calculationService.listenCalculation()
                .collect {
                    _calculation.value = it
                }
        }

        viewModelScope.launch {
            historyRepository.getAll().collect {
                if (it.isNotEmpty()) {
                    _calculation.value = it.last().toCalculation()
                }
            }
        }
    }

    fun eraseOne(): Unit = calculationService.eraseOne()

    fun erase(): Boolean = calculationService.erase()

    fun setOperation(operation: String): Unit = calculationService.setOperation(operation)

    fun addDigit(digit: String): Unit = calculationService.addDigit(digit)

    fun getResult() {
        viewModelScope.launch {
//            try {
                calculationService.getResult()
//            } catch (e: Exception) {
//                showErrorToast()
//            }
        }
    }

    private fun showErrorToast() = _showToastEvent.publishEvent(R.string.error)
}