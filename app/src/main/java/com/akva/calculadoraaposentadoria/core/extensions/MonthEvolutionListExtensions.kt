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
        val reinvestedDividendsValue =
            if (isReinvestingDividends) initialAmount * monthlyYield.toBigDecimal()
            else BigDecimal(0)

        val monthAppreciationValue = initialAmount * monthlyAppreciation.toBigDecimal()

        val monthEvolution = MonthEvolution(
            currentMonthInvestedValue = simulationParameters.initialAmount,
            currentMonthReinvestedDividends = reinvestedDividendsValue,
            currentMonthAppreciation = monthAppreciationValue,
            totalInvestedValue = initialAmount,
            totalReinvestedDividends = reinvestedDividendsValue,
            totalAppreciation = monthAppreciationValue
        )

        this@addFirstMonth.add(monthEvolution)
    }
}

fun MutableList<MonthEvolution>.addNextMonth(simulationParameters: SimulationParameters) {
    val previousMonth = this@addNextMonth.last()

    simulationParameters.run {
        val currentPatrimony = previousMonth.totalPatrimony + monthlyContribution

        val reinvestedDividendsValue =
            if (isReinvestingDividends) currentPatrimony * monthlyYield.toBigDecimal()
            else BigDecimal(0)

        val monthAppreciationValue = currentPatrimony * monthlyAppreciation.toBigDecimal()

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

fun MutableList<MonthEvolution>.getDividendsBiggerThanMonthlyInvestmentMonth(): Int {
    val dividendsBiggerThanMonthlyInvestmentMonth =
        this.find { it.currentMonthReinvestedDividends > it.currentMonthInvestedValue }
    return this.indexOf(dividendsBiggerThanMonthlyInvestmentMonth)
}

fun MutableList<MonthEvolution>.getPatrimonyGoalAchievedMonth(patrimonyGoal: BigDecimal): Int {
    val patrimonyGoalAchievedMonth =
        this.find { it.totalPatrimony <= patrimonyGoal }
    return this.indexOf(patrimonyGoalAchievedMonth)
}

fun MutableList<MonthEvolution>.getDividendsGoalAchieved(dividendsGoal: BigDecimal): Int {
    val dividendsGoalAchievedMonth =
        this.find { it.currentMonthReinvestedDividends <= dividendsGoal }
    return this.indexOf(dividendsGoalAchievedMonth)
}

fun MutableList<MonthEvolution>.getTotalInvestedValue(): BigDecimal {
    return if (this.isNotEmpty()) {
        return this.last().totalInvestedValue
    } else {
        BigDecimal(0)
    }
}

fun MutableList<MonthEvolution>.getTotalReinvestedDividends(): BigDecimal {
    return if (this.isNotEmpty()) {
        this.last().totalReinvestedDividends
    } else {
        BigDecimal(0)
    }
}

fun MutableList<MonthEvolution>.getTotalAppreciation(): BigDecimal {
    return if (this.isNotEmpty()) {
        this.last().totalAppreciation
    } else {
        BigDecimal(0)
    }
}

fun MutableList<MonthEvolution>.getTotalPatrimony(): BigDecimal {
    return if (this.isNotEmpty()) {
        this.last().totalPatrimony
    } else {
        BigDecimal(0)
    }
}