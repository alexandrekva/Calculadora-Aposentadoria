package com.akva.calculadoraaposentadoria.core.extensions

import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.MonthEvolution
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.SimulationParameters
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.YearEvolution
import java.math.BigDecimal
import java.util.*

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
            totalPatrimony = firstMonthInvestment
        )

        this@addFirstMonth.add(monthEvolution)
    }
}

fun MutableList<MonthEvolution>.addNextMonth(simulationParameters: SimulationParameters) {
    val previousMonth = this.last()

    simulationParameters.run {
        val currentMonthDividends = previousMonth.totalPatrimony * monthlyYield.toBigDecimal()
        val currentMonthAppreciation =
            previousMonth.totalPatrimony * monthlyAppreciation.toBigDecimal()
        val totalInvestedValue = previousMonth.totalInvestedValue + monthlyContribution
        val totalDividends = previousMonth.totalDividends + currentMonthDividends
        val totalAppreciation = previousMonth.totalAppreciation + currentMonthAppreciation
        val totalPatrimony = if (isReinvestingDividends) {
            totalAppreciation + totalInvestedValue + totalAppreciation
        } else {
            totalInvestedValue + totalAppreciation
        }

        val monthEvolution = MonthEvolution(
            currentMonthInvestedValue = monthlyContribution,
            currentMonthDividends = currentMonthDividends,
            currentMonthAppreciation = currentMonthAppreciation,
            totalInvestedValue = totalInvestedValue,
            totalDividends = totalDividends,
            totalAppreciation = totalAppreciation,
            totalPatrimony = totalPatrimony
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
    return (this.last().currentMonthDividends > monthlyContribution)
}

fun MutableList<MonthEvolution>.getDividendsSurpassedMonthlyContributionMonth(): Int {
    val dividendsBiggerThanMonthlyInvestmentMonth =
        this.find { it.currentMonthDividends > it.currentMonthInvestedValue }
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
            this.find { it.currentMonthDividends >= dividendsGoal }
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

fun MutableList<MonthEvolution>.getTotalDividends(): BigDecimal {
    return if (this.isNotEmpty()) {
        this.last().totalDividends
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

fun List<MonthEvolution>.getWindowTotalPatrimony(): BigDecimal {
    return this.last().totalPatrimony
}

fun List<MonthEvolution>.getWindowTotalDividends(): BigDecimal {
    var totalDividends = BigDecimal.ZERO

    this.map { totalDividends += it.currentMonthDividends }

    return totalDividends
}

fun List<MonthEvolution>.getWindowTotalInvested(): BigDecimal {
    var totalInvested = BigDecimal.ZERO

    this.map { totalInvested += it.currentMonthInvestedValue }

    return totalInvested
}

fun List<MonthEvolution>.getWindowTotalAppreciation(): BigDecimal {
    var totalAppreciation = BigDecimal.ZERO

    this.map { totalAppreciation += it.currentMonthAppreciation }

    return totalAppreciation
}

fun MutableList<MonthEvolution>.getYearByYearEvolution(): List<YearEvolution> {
    val year = Calendar.getInstance().get(Calendar.YEAR)
    val yearWindows = this.windowed(size = 12, step = 12)

    return yearWindows.mapIndexed { index, monthEvolutions ->
        YearEvolution(
            year = year + index + 1,
            totalPatrimony = monthEvolutions.getWindowTotalPatrimony(),
            totalInvestedInYear = monthEvolutions.getWindowTotalInvested(),
            totalDividendsInYear = monthEvolutions.getWindowTotalDividends(),
            totalAppreciationInYear = monthEvolutions.getWindowTotalAppreciation()
        )
    }
}