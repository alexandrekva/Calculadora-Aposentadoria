package com.akva.calculadoraaposentadoria.feature_simulation.presentation.input_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.akva.calculadoraaposentadoria.core.extensions.toBigDecimalFromInput
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.SimulationParameters

class InputScreenViewModel : ViewModel() {

    var initialAmount = mutableStateOf("")
        private set

    var monthlyContribution = mutableStateOf("")
        private set

    var monthlyYield = mutableStateOf("")
        private set

    var monthlyAppreciation = mutableStateOf("")
        private set

    var years = mutableStateOf(35f)
        private set

    var isReinvestingDividends = mutableStateOf(true)
        private set

    var dialogState = mutableStateOf<DialogState>(DialogState.Closed)
        private set

    var  menuState = mutableStateOf(false)
        private set

    fun setDialogOpen(title: String, description: String) {
        dialogState.value = DialogState.Open(title = title, description = description)
    }

    fun setDialogClosed() {
        dialogState.value = DialogState.Closed
    }

    fun setMenuState(boolean: Boolean) {
        menuState.value = boolean
    }

    fun getSimulationParameters(): SimulationParameters {
        val initialAmount = this.initialAmount.value.toBigDecimalFromInput()
        val monthlyContribution = this.monthlyContribution.value.toBigDecimalFromInput()
        val monthlyAppreciation = this.monthlyAppreciation.value.toBigDecimalFromInput()
        val monthlyYield = this.monthlyYield.value.toBigDecimalFromInput()
        val isReinvestingDividends = this.isReinvestingDividends.value
        val years = this.years.value

        return SimulationParameters(
            initialAmount = initialAmount,
            monthlyContribution = monthlyContribution,
            monthlyYield = (monthlyYield.toFloat() / 100),
            monthlyAppreciation = (monthlyAppreciation.toFloat() / 100),
            years = years.toInt(),
            isReinvestingDividends = isReinvestingDividends
        )
    }
}