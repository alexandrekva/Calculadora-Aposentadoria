package com.akva.calculadoraaposentadoria.core.extensions

fun Int.toYearsAndMonthsString(): String? {
    val year = this/12
    val month = this%12
    val yearString = when(year) {
        0 -> null
        1 -> "$year ano"
        else -> "$year anos"
    }
    val monthString = when(month) {
        0 -> null
        1 -> "$month mês"
        else -> "$month meses"
    }
    return when {
        yearString == null && monthString == null -> null
        yearString != null && monthString == null -> yearString
        yearString == null && monthString != null -> monthString
        else -> "$yearString e $monthString"
    }
}