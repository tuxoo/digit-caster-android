package com.tuxoo.digit_caster_android.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuxoo.digit_caster_android.R
import com.tuxoo.digit_caster_android.model.calculation.CalculationListener
import com.tuxoo.digit_caster_android.model.calculation.CalculationService
import com.tuxoo.digit_caster_android.model.calculation.entity.Calculation
import com.tuxoo.digit_caster_android.util.MutableLiveEvent
import com.tuxoo.digit_caster_android.util.publishEvent
import com.tuxoo.digit_caster_android.util.share
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculationViewModel @Inject constructor(
    private val calculationService: CalculationService,
) : ViewModel() {

    private val _calculation = MutableLiveData<Calculation>()
    val calculation: LiveData<Calculation> = _calculation

    private val _showToastEvent = MutableLiveEvent<Int>()
    val showToastEvent = _showToastEvent.share()

    private val listener: CalculationListener = {
        _calculation.value = it
    }

    init {
        calculationService.addListener(listener)
    }

    fun eraseOne(): Unit = calculationService.eraseOne()

    fun erase(): Boolean = calculationService.erase()

    fun setOperation(operation: String): Unit = calculationService.setOperation(operation)

    fun addDigit(digit: String): Unit = calculationService.addDigit(digit)

    fun getResult() {
        viewModelScope.launch {
            try {
                calculationService.getResult()
            } catch (e: Exception) {
                showErrorToast()
            }
        }
    }

    private fun showErrorToast() = _showToastEvent.publishEvent(R.string.error)

    override fun onCleared() {
        super.onCleared()
        calculationService.removeListener(listener)
    }

    companion object {
        private const val CALCULATION_STATE = "calculation_state"
    }
}