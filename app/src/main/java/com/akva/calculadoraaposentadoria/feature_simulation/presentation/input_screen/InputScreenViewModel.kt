package com.akva.calculadoraaposentadoria.feature_simulation.presentation.input_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.akva.calculadoraaposentadoria.feature_simulation.domain.SimulationParameters
import java.math.BigDecimal

class InputScreenViewModel : ViewModel() {

    var initialAmount = mutableStateOf("")
        private set

    var monthlyContribution = mutableStateOf("")
        private set

    var monthlyYield = mutableStateOf(0f)
        private set

    var monthlyAppreciation = mutableStateOf(0f)
        private set

    var years = mutableStateOf(35f)
        private set

    var isReinvestingDividends = mutableStateOf(true)
        private set

    fun getSimulationParameters(): SimulationParameters {
        return SimulationParameters(
            initialAmount = initialAmount.value.toBigDecimal(),
            monthlyContribution = monthlyContribution.value.toBigDecimal(),
            monthlyYield = monthlyYield.value,
            monthlyAppreciation = monthlyAppreciation.value,
            years = years.value.toInt(),
            isReinvestingDividends = isReinvestingDividends.value
        )
    }

}