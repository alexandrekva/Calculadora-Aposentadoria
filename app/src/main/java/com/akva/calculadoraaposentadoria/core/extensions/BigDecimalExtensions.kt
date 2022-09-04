package com.akva.calculadoraaposentadoria.core.extensions

import java.math.BigDecimal
import java.text.DecimalFormat

fun BigDecimal.toCurrencyFormat(): String {
    val formatter = DecimalFormat("#,###,##0.00")
    return formatter.format(this)
}