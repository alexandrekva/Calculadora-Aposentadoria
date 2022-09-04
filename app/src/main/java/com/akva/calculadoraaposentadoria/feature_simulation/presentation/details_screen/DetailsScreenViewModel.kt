package com.akva.calculadoraaposentadoria.feature_simulation.presentation.details_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.SimulationDetails
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.SimulationParameters
import com.akva.calculadoraaposentadoria.feature_simulation.utils.CreateMonthEvolutionListFromParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsScreenViewModel : ViewModel() {

    var detailsScreenViewState =
        mutableStateOf<DetailsScreenViewState>(DetailsScreenViewState.Loading)
        private set

    fun simulate(simulationParameters: SimulationParameters) {
        viewModelScope.launch(Dispatchers.Default) {
            val monthEvolutionList =
                CreateMonthEvolutionListFromParameters(simulationParameters)
            detailsScreenViewState.value =
                DetailsScreenViewState.Loaded(SimulationDetails(monthEvolutionList))
        }
    }

}