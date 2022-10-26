package com.tuxoo.digit_caster_android.screens.calculation

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuxoo.digit_caster_android.R
import com.tuxoo.digit_caster_android.model.calculation.CalculationService
import com.tuxoo.digit_caster_android.model.calculation.entity.Calculation
import com.tuxoo.digit_caster_android.util.MutableLiveEvent
import com.tuxoo.digit_caster_android.util.publishEvent
import com.tuxoo.digit_caster_android.util.share
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class CalculationViewModel @AssistedInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    private val calculationService: CalculationService,
) : ViewModel() {

    private val _calculation = savedStateHandle.getLiveData(CALCULATION_STATE, Calculation())
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

    companion object {
        private const val CALCULATION_STATE = "calculation_state"
    }
}