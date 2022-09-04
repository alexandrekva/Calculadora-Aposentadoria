package com.akva.calculadoraaposentadoria.navigation

import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.navArgument
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.SimulationParameters
import com.akva.calculadoraaposentadoria.feature_simulation.presentation.details_screen.DetailsScreen
import com.akva.calculadoraaposentadoria.feature_simulation.presentation.input_screen.InputScreen

@RequiresApi(33)
@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screens.InputScreen.route) {
            InputScreen(
                onNavigationToDetails = { simulationParameters: String ->
                    val routeWithArgs =
                        Screens.DetailsScreen.passSimulationParameters(simulationParameters)
                    navController.navigate(routeWithArgs)
                }
            )
        }
        composable(
            route = Screens.DetailsScreen.route, arguments = listOf(
                navArgument(SIMULATION_PARAMETERS_ARGS_KEY) {
                    type = SimulationParameters.NavigationType
                }
            )
        ) { backStackEntry ->
            val simulationParameters =
                backStackEntry.arguments?.getParcelable<SimulationParameters>(
                    SIMULATION_PARAMETERS_ARGS_KEY
                )

            DetailsScreen(simulationParameters = simulationParameters)
        }
    }

}