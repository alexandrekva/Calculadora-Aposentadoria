package com.akva.calculadoraaposentadoria.feature_simulation.domain.entities

import java.math.BigDecimal

data class YearEvolution(
    val year: Int,
    val totalPatrimony: BigDecimal,
    val totalInvestedInYear: BigDecimal,
    val totalDividendsInYear: BigDecimal,
    val totalAppreciationInYear: BigDecimal,
)