package com.akva.calculadoraaposentadoria.feature_simulation.domain.entities

import java.math.BigDecimal

data class MonthEvolution(
    val currentMonthInvestedValue: BigDecimal = BigDecimal(0),
    val currentMonthReinvestedDividends: BigDecimal = BigDecimal(0),
    val currentMonthAppreciation: BigDecimal = BigDecimal(0),
    val totalInvestedValue: BigDecimal = BigDecimal(0),
    val totalReinvestedDividends: BigDecimal = BigDecimal(0),
    val totalAppreciation: BigDecimal = BigDecimal(0),
    val totalPatrimony: BigDecimal = totalInvestedValue + totalReinvestedDividends + totalAppreciation
)