package com.akva.calculadoraaposentadoria.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.akva.calculadoraaposentadoria.feature_simulation.presentation.input_screen.InputScreen

@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screens.InputScreen.route) {
            InputScreen(
                onNavigationToDetails = { simulationParameters: String ->
//                    val routeWithArgs = Screens.Details.passSimulationParameters(simulationParameters)
//                    navController.navigate(routeWithArgs)
                }
            )
        }
//        composable(
//            route = Screens.Details.route, arguments = listOf(
//                navArgument(SIMULATION_PARAMETERS_ARGS_KEY) {
//                    type = SimulationParameters.NavigationType
//                }
//            )
//        ) { backStackEntry ->
//            val simulationParameters =
//                backStackEntry.arguments?.getParcelable<SimulationParameters>(
//                    SIMULATION_PARAMETERS_ARGS_KEY
//                )
//            DetailsScreen(simulationParameters)
//        }
    }

}