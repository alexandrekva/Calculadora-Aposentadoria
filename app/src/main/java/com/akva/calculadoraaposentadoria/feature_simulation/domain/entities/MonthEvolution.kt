package com.akva.calculadoraaposentadoria.feature_simulation.domain.entities

import java.math.BigDecimal

data class MonthEvolution(
    val currentMonthInvestedValue: BigDecimal = BigDecimal.ZERO,
    val currentMonthDividends: BigDecimal = BigDecimal.ZERO,
    val currentMonthAppreciation: BigDecimal = BigDecimal.ZERO,
    val totalInvestedValue: BigDecimal = BigDecimal.ZERO,
    val totalDividends: BigDecimal = BigDecimal.ZERO,
    val totalAppreciation: BigDecimal = BigDecimal.ZERO,
    val totalPatrimony: BigDecimal = BigDecimal.ZERO
)
