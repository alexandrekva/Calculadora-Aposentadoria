package com.akva.calculadoraaposentadoria.core.extensions

import android.content.Context
import com.akva.calculadoraaposentadoria.R

fun Int.toYearsAndMonthsString(context: Context): String? {
    val year = this / 12
    val month = this % 12

    val yearString = "$year "  + context.resources.getQuantityString(R.plurals.stringPluralAnos, year)
    val monthString = "$month " + context.resources.getQuantityString(R.plurals.stringPluralMeses, month)
    val andString = context.resources.getString(R.string.e)

    return when {
        yearString.isEmpty() && monthString.isEmpty() -> null
        yearString.isNotEmpty() && monthString.isEmpty() -> yearString
        yearString.isEmpty() && monthString.isNotEmpty() -> monthString
        else -> "$yearString $andString $monthString"
    }
}