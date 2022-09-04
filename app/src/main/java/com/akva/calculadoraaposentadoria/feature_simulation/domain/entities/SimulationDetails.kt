package com.akva.calculadoraaposentadoria.feature_simulation.domain.entities

import com.akva.calculadoraaposentadoria.core.extensions.*
import java.math.BigDecimal

data class SimulationDetails(val monthEvolutionList: MutableList<MonthEvolution>) {
    val totalInvested: BigDecimal = monthEvolutionList.getTotalInvestedValue()
    val totalInvestedString: String = monthEvolutionList.getTotalInvestedValue().toCurrencyFormat()
    val totalReinvestedDividends: BigDecimal = monthEvolutionList.getTotalReinvestedDividends()
    val totalReinvestedDividendsString: String = monthEvolutionList.getTotalReinvestedDividends().toCurrencyFormat()
    val totalAppreciation: BigDecimal = monthEvolutionList.getTotalAppreciation()
    val totalAppreciationString: String = monthEvolutionList.getTotalAppreciation().toCurrencyFormat()
    val totalPatrimony: BigDecimal = monthEvolutionList.getTotalPatrimony()
    val totalPatrimonyString: String = monthEvolutionList.getTotalPatrimony().toCurrencyFormat()
    val firstMillionAchievedMonth: Int = monthEvolutionList.getMillionAchievedMonth()
    val patrimonyGoalAchievedMonth: Int = -1
    val dividendsGoalAchievedMonth: Int = -1
}
