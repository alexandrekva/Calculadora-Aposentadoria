package com.akva.calculadoraaposentadoria.core.extensions

import java.math.BigDecimal
import java.text.DecimalFormat

fun String.toCurrencyFormat(): String {
    val formatter = DecimalFormat("#,###,##0.00")
    val value = this
        .ifBlank { "0" }
        .toBigDecimal()
        .divide(100.toBigDecimal())

    return formatter.format(value)
}

fun String.toBigDecimalFromInput(): BigDecimal {
    return this
        .ifBlank { "0" }
        .toBigDecimal()
        .divide(100.toBigDecimal())

}