package com.akva.calculadoraaposentadoria.core.extensions

import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.MonthEvolution
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.SimulationParameters
import java.math.BigDecimal

fun MutableList<MonthEvolution>.addMonth(simulationParameters: SimulationParameters) {
    if (this.size == 0) {
        this.addFirstMonth(simulationParameters)
    } else {
        this.addNextMonth(simulationParameters)
    }
}

fun MutableList<MonthEvolution>.addFirstMonth(simulationParameters: SimulationParameters) {
    simulationParameters.run {
        val firstMonthInvestment =
            if (initialAmount != BigDecimal(0)) initialAmount else monthlyContribution

        val monthEvolution = MonthEvolution(
            currentMonthInvestedValue = firstMonthInvestment,
            totalInvestedValue = firstMonthInvestment,
        )

        this@addFirstMonth.add(monthEvolution)
    }
}

fun MutableList<MonthEvolution>.addNextMonth(simulationParameters: SimulationParameters) {
    val previousMonth = this@addNextMonth.last()

    simulationParameters.run {
        val reinvestedDividendsValue =
            if (isReinvestingDividends) previousMonth.totalPatrimony * monthlyYield.toBigDecimal()
            else BigDecimal.ZERO

        val monthAppreciationValue =
            previousMonth.totalPatrimony * monthlyAppreciation.toBigDecimal()

        val monthEvolution = MonthEvolution(
            currentMonthInvestedValue = monthlyContribution,
            currentMonthReinvestedDividends = reinvestedDividendsValue,
            currentMonthAppreciation = monthAppreciationValue,
            totalInvestedValue = previousMonth.totalInvestedValue + monthlyContribution,
            totalReinvestedDividends = previousMonth.totalReinvestedDividends + reinvestedDividendsValue,
            totalAppreciation = previousMonth.totalAppreciation + monthAppreciationValue
        )

        this@addNextMonth.add(monthEvolution)
    }
}

fun MutableList<MonthEvolution>.getMillionAchievedMonth(): Int {
    val millionAchievedMonth = this.find { it.totalPatrimony > BigDecimal(1000000) }
    return this.indexOf(millionAchievedMonth)
}

fun MutableList<MonthEvolution>.getFinalMonthlyDividends(monthlyYield: Float): BigDecimal {
    return last().totalPatrimony * monthlyYield.toBigDecimal()
}

fun MutableList<MonthEvolution>.hasDividendsSurpassedMonthlyContribution(monthlyContribution: BigDecimal): Boolean {
    return (this.last().currentMonthReinvestedDividends > monthlyContribution)
}

fun MutableList<MonthEvolution>.getDividendsSurpassedMonthlyContributionMonth(): Int {
    val dividendsBiggerThanMonthlyInvestmentMonth =
        this.find { it.currentMonthReinvestedDividends > it.currentMonthInvestedValue }
    return this.indexOf(dividendsBiggerThanMonthlyInvestmentMonth)
}

fun MutableList<MonthEvolution>.getPatrimonyGoalAchievedMonth(patrimonyGoal: BigDecimal): Int {
    return if (patrimonyGoal != BigDecimal.ZERO) {
        val patrimonyGoalAchievedMonth =
            this.find { it.totalPatrimony <= patrimonyGoal }
        this.indexOf(patrimonyGoalAchievedMonth)
    } else {
        -1
    }

}

fun MutableList<MonthEvolution>.getDividendsGoalAchieved(dividendsGoal: BigDecimal): Int {
    return if (dividendsGoal != BigDecimal.ZERO) {
        val dividendsGoalAchievedMonth =
            this.find { it.currentMonthReinvestedDividends >= dividendsGoal }
        this.indexOf(dividendsGoalAchievedMonth)
    } else {
        -1
    }
}

fun MutableList<MonthEvolution>.getTotalInvestedValue(): BigDecimal {
    return if (this.isNotEmpty()) {
        return this.last().totalInvestedValue
    } else {
        BigDecimal.ZERO
    }
}

fun MutableList<MonthEvolution>.getTotalReinvestedDividends(): BigDecimal {
    return if (this.isNotEmpty()) {
        this.last().totalReinvestedDividends
    } else {
        BigDecimal.ZERO
    }
}

fun MutableList<MonthEvolution>.getTotalAppreciation(): BigDecimal {
    return if (this.isNotEmpty()) {
        this.last().totalAppreciation
    } else {
        BigDecimal.ZERO
    }
}

fun MutableList<MonthEvolution>.getTotalPatrimony(): BigDecimal {
    return if (this.isNotEmpty()) {
        this.last().totalPatrimony
    } else {
        BigDecimal.ZERO
    }
}

fun MutableList<MonthEvolution>.getYears(): List<MonthEvolution> {
    return this.filterIndexed { index, _ ->  index != 0 && index % 11 == 0}
}