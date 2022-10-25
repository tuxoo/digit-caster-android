package com.tuxoo.digit_caster_android.model.calculation

import com.tuxoo.digit_caster_android.model.calculation.entity.Calculation
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

typealias CalculationListener = (calculation: Calculation) -> Unit

class CalculationService(
    private val calculationSource: CalculationSource
) {

    private var calculation = Calculation()
    private val listeners = mutableSetOf<CalculationListener>()

    fun eraseOne() {
        calculation.currentNum = calculation.currentNum.dropLast(1)
        notifyChanges()
    }

    fun erase(): Boolean {
        calculation.currentNum = ""
        calculation.operation = ""
        calculation.previousNum = ""
        notifyChanges()
        return true
    }

    fun addDigit(digit: String) {
        calculation.currentNum += digit
        notifyChanges()
    }

    fun setOperation(operation: String): Unit =
        with(calculation) {
            if (currentNum.isNotBlank() || previousNum.isNotBlank()) {
                this.operation = operation
            }

            if (previousNum.isBlank()) {
                previousNum = currentNum
                currentNum = ""
            }
            notifyChanges()
        }

    suspend fun getResult() {
        val result = calculationSource.calculate(calculation)
        calculation.currentNum = result
        calculation.operation = ""
        calculation.previousNum = ""
        notifyChanges()
    }

    fun listenCalculation(): Flow<Calculation> = callbackFlow {
        val listener: CalculationListener = {
            trySend(it)
        }
        listeners.add(listener)

        awaitClose {
            listeners.remove(listener)
        }
    }.buffer(Channel.CONFLATED)

    private fun notifyChanges(): Unit =
        listeners.forEach {
            it.invoke(calculation)
        }
}