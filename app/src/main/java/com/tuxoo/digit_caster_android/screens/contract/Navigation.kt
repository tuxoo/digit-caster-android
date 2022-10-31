package com.tuxoo.digit_caster_android.screens.contract

import androidx.fragment.app.Fragment

fun Fragment.navigation(): Navigation = requireContext() as Navigation

interface Navigation {

    fun showCalculator()

    fun showHistory()

    fun showHelp()
}