package com.tuxoo.digit_caster_android.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuxoo.digit_caster_android.model.calculation.CalculationService
import com.tuxoo.digit_caster_android.model.history.HistoryRepository
import com.tuxoo.digit_caster_android.screens.calculation.CalculationViewModel
import com.tuxoo.digit_caster_android.screens.history.HistoryViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val calculationService: CalculationService,
    private val historyRepository: HistoryRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            CalculationViewModel::class.java -> CalculationViewModel(
                calculationService
            )
            HistoryViewModel::class.java -> HistoryViewModel(historyRepository)
            else -> error("Unknown view model class")
        }

        return viewModel as T
    }
}