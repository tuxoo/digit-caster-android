package com.tuxoo.digit_caster_android.model

typealias CalculationListener = (calculation: Calculation) -> Unit

class CalculationService {

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
            if (previousNum.isBlank()) {
                previousNum = currentNum
                currentNum = ""
            }

            this.operation = operation
            notifyChanges()
        }

    fun addListener(listener: CalculationListener) {
        listeners.add(listener)
        listener.invoke(calculation)
    }

    fun removeListener(listener: CalculationListener): Boolean =
        listeners.remove(listener)

    private fun notifyChanges(): Unit =
        listeners.forEach {
            it.invoke(calculation)
        }

}