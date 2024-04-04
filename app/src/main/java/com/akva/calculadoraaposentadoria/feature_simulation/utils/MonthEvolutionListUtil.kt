package com.akva.calculadoraaposentadoria.feature_simulation.utils

import com.akva.calculadoraaposentadoria.core.extensions.addMonth
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.MonthEvolution
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.SimulationParameters


fun CreateMonthEvolutionListFromParameters(simulationParameters: SimulationParameters): MutableList<MonthEvolution> {
    val monthEvolutionList = mutableListOf<MonthEvolution>()

    for (i in 0 until simulationParameters.months) {
        monthEvolutionList.addMonth(simulationParameters)
    }

    return monthEvolutionList
}