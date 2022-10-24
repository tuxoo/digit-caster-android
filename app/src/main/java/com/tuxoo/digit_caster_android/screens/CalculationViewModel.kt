package com.tuxoo.digit_caster_android.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuxoo.digit_caster_android.model.calculation.CalculationListener
import com.tuxoo.digit_caster_android.model.calculation.CalculationService
import com.tuxoo.digit_caster_android.model.calculation.entity.Calculation
import kotlinx.coroutines.launch

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

    fun getResult() {
        viewModelScope.launch {
            try {
                calculationService.getResult()
            } catch (e: Exception) {
                Log.d("View", "Something went wrong")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        calculationService.removeListener(listener)
    }

    companion object {
        private const val CALCULATION_STATE = "calculation_state"
    }
}