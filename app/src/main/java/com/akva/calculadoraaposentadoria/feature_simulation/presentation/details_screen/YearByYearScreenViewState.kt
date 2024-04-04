package com.akva.calculadoraaposentadoria.feature_simulation.presentation.details_screen

import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.YearEvolution

sealed class YearByYearScreenViewState {
    object Loading: YearByYearScreenViewState()
    data class Loaded(val yearList: List<YearEvolution>): YearByYearScreenViewState()
}