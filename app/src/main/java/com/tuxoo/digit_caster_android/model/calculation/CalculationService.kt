package com.tuxoo.digit_caster_android.model.calculation

import com.tuxoo.digit_caster_android.model.calculation.entity.Calculation
import com.tuxoo.digit_caster_android.model.calculation.entity.CalculationWithResult
import com.tuxoo.digit_caster_android.model.history.HistoryRepository
import com.tuxoo.digit_caster_android.model.history.entity.History
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

typealias CalculationListener = (calculation: Calculation) -> Unit

@Singleton
class CalculationService @Inject constructor(
    private val calculationSource: CalculationSource,
    private val historyRepository: HistoryRepository
) {

    private lateinit var calculation : Calculation
    private val listeners = mutableSetOf<CalculationListener>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            historyRepository.getAll().collect {
                if (it.isNotEmpty()) {
                    calculation = it.last().toCalculation()
                }
            }
        }
    }

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

//        val withResult = calculation.toCalculationWithResult(result)
//        withContext(Dispatchers.IO) {
//            saveResult(withResult)
//        }

        calculation.currentNum = result
        calculation.operation = ""
        calculation.previousNum = ""

        notifyChanges()
    }

    private suspend fun saveResult(calculation: CalculationWithResult): Unit =
        historyRepository.add(
            History(
                operation = calculation.operation,
                firstNum = calculation.previousNum,
                secondNum = calculation.currentNum,
                result = calculation.result,
            )
        )

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