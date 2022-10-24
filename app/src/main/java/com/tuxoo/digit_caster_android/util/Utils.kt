package com.tuxoo.digit_caster_android.util

import android.app.Activity
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.tuxoo.digit_caster_android.App
import com.tuxoo.digit_caster_android.screens.CalculationViewModel

class ViewModelFactory(
    private val app: App,
    owner: SavedStateRegistryOwner
) : AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        val viewModel = when (modelClass) {
            CalculationViewModel::class.java -> CalculationViewModel(app.calculationService, handle)
            else -> error("Unknown view model class")
        }

        return viewModel as T
    }
}

fun Activity.factory(owner: SavedStateRegistryOwner) =
    ViewModelFactory(applicationContext as App, owner)