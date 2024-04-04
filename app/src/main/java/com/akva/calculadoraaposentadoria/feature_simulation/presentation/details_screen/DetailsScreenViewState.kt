package com.akva.calculadoraaposentadoria.feature_simulation.presentation.details_screen

import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.SimulationDetails

sealed class DetailsScreenViewState {
    object Loading: DetailsScreenViewState()
    data class Loaded(val simulationDetails: SimulationDetails): DetailsScreenViewState()
}
