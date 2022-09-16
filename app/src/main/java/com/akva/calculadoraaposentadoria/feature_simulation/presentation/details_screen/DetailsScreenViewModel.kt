package com.akva.calculadoraaposentadoria.feature_simulation.presentation.details_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akva.calculadoraaposentadoria.core.extensions.getYearByYearEvolution
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.MonthEvolution
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.SimulationDetails
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.SimulationParameters
import com.akva.calculadoraaposentadoria.feature_simulation.utils.CreateMonthEvolutionListFromParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsScreenViewModel : ViewModel() {

    var detailsScreenViewState =
        mutableStateOf<DetailsScreenViewState>(DetailsScreenViewState.Loading)
        private set

    var yearByYearScreenViewState =
        mutableStateOf<YearByYearScreenViewState>(YearByYearScreenViewState.Loading)
        private set

    private var monthEvolutionList: MutableList<MonthEvolution> = mutableListOf()

    fun simulate(simulationParameters: SimulationParameters) {
        viewModelScope.launch(Dispatchers.Default) {
            monthEvolutionList =
                CreateMonthEvolutionListFromParameters(simulationParameters)
            detailsScreenViewState.value =
                DetailsScreenViewState.Loaded(
                    SimulationDetails(
                        monthEvolutionList = monthEvolutionList,
                        simulationParameters = simulationParameters
                    )
                )
        }
    }

    fun getYearByYearEvolution() {
        viewModelScope.launch(Dispatchers.Default) {
            val yearList = monthEvolutionList.getYearByYearEvolution()
            yearByYearScreenViewState.value = YearByYearScreenViewState.Loaded(yearList)
        }
    }
}