package com.tuxoo.digit_caster_android.screens

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuxoo.digit_caster_android.App

class ViewModelFactory(
    private val app: App
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            CalculationViewModel::class.java -> CalculationViewModel(app.calculationService)
            else -> error("Unknown view model class")
        }

        return viewModel as T
    }
}

fun Activity.factory() = ViewModelFactory(applicationContext as App)