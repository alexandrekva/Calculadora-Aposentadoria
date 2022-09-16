package com.akva.calculadoraaposentadoria.navigation

const val INPUT_SCREEN_ROUTE = "input_screen"
const val DETAILS_SCREEN_ROUTE = "details_screen"
const val UNDERSTAND_SIMULATION_SCREEN_ROUTE = "understand_simulation_screen"
const val YEAR_BY_YEAR_SCREEN_ROUTE = "year_by_year_screen"
const val SIMULATION_PARAMETERS_ARGS_KEY = "simulationParameters"

sealed class Screens(val route: String) {
    object InputScreen : Screens(route = INPUT_SCREEN_ROUTE)
    object UnderstandSimulationScreen : Screens(route = UNDERSTAND_SIMULATION_SCREEN_ROUTE)
    object YearByYearScreen : Screens(route = YEAR_BY_YEAR_SCREEN_ROUTE)
    object DetailsScreen : Screens(route = "$DETAILS_SCREEN_ROUTE/{$SIMULATION_PARAMETERS_ARGS_KEY}") {
        fun passSimulationParameters(simulationParameters: String): String {
            return "$DETAILS_SCREEN_ROUTE/$simulationParameters"
        }
    }
}