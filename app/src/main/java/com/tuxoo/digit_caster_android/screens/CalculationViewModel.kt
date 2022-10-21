package com.tuxoo.digit_caster_android.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.tuxoo.digit_caster_android.model.Calculation
import com.tuxoo.digit_caster_android.model.CalculationListener
import com.tuxoo.digit_caster_android.model.CalculationService

class CalculationViewModel(
    private val calculationService: CalculationService,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _calculation = savedStateHandle.getLiveData(CALCULATION_STATE, Calculation())
    val calculation: LiveData<Calculation> = _calculation

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

    override fun onCleared() {
        super.onCleared()
        calculationService.removeListener(listener)
    }

    companion object {
        private const val CALCULATION_STATE = "calculation_state"
    }
}