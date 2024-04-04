package com.akva.calculadoraaposentadoria.feature_simulation.domain.entities

import com.akva.calculadoraaposentadoria.core.extensions.*
import java.math.BigDecimal

data class SimulationDetails(
    val monthEvolutionList: MutableList<MonthEvolution>,
    val simulationParameters: SimulationParameters
) {
    val totalInvested: BigDecimal = monthEvolutionList.getTotalInvestedValue()
    val totalDividends: BigDecimal = monthEvolutionList.getTotalDividends()
    val totalAppreciation: BigDecimal = monthEvolutionList.getTotalAppreciation()
    val totalPatrimony: BigDecimal = monthEvolutionList.getTotalPatrimony()
    val finalMonthlyDividends: BigDecimal =
        monthEvolutionList.getFinalMonthlyDividends(simulationParameters.monthlyYield)

    val hasDividendsSurpassedMonthlyContribution =
        monthEvolutionList.hasDividendsSurpassedMonthlyContribution(simulationParameters.monthlyContribution)
    val dividendsSurpassedMonthlyContributionMonth =
        monthEvolutionList.getDividendsSurpassedMonthlyContributionMonth()

    val firstMillionAchievedMonth: Int = monthEvolutionList.getMillionAchievedMonth()

    val hasPatrimonyGoal = simulationParameters.patrimonyGoal != BigDecimal.ZERO
    val patrimonyGoalAchievedMonth: Int =
        monthEvolutionList.getPatrimonyGoalAchievedMonth(simulationParameters.patrimonyGoal)

    val hasDividendsGoal = simulationParameters.dividendsGoal != BigDecimal.ZERO
    val dividendsGoalAchievedMonth: Int =
        monthEvolutionList.getDividendsGoalAchieved(simulationParameters.dividendsGoal)
}
