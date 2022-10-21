package com.tuxoo.digit_caster_android.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Calculation(
    var previousNum: String = "",
    var currentNum: String = "",
    var operation: String = "",
) : Parcelable
