package com.tuxoo.digit_caster_android.util

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.tuxoo.digit_caster_android.model.calculation.CalculationService
import com.tuxoo.digit_caster_android.model.calculation.HistoryService
import com.tuxoo.digit_caster_android.screens.CalculationViewModel
import com.tuxoo.digit_caster_android.screens.HistoryViewModel
import javax.inject.Inject

interface ViewModelAssistedFactory<T : ViewModel> {
    fun create(handle: SavedStateHandle): T
}

class CalculationViewModelFactory @Inject constructor(
    private val calculationService: CalculationService
) : ViewModelAssistedFactory<CalculationViewModel> {
    override fun create(handle: SavedStateHandle): CalculationViewModel {
        return CalculationViewModel(handle, calculationService)
    }
}

class HistoryViewModelFactory @Inject constructor(
    private val historyService: HistoryService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HistoryViewModel(historyService) as T
    }
}

class GenericSavedStateViewModelFactory<out V : ViewModel>(
    private val viewModelFactory: ViewModelAssistedFactory<V>,
    owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return viewModelFactory.create(handle) as T
    }
}