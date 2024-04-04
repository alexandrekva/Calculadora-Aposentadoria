package com.akva.calculadoraaposentadoria.navigation

import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.SimulationParameters
import com.akva.calculadoraaposentadoria.feature_simulation.presentation.details_screen.DetailsScreen
import com.akva.calculadoraaposentadoria.feature_simulation.presentation.details_screen.YearByYearScreen
import com.akva.calculadoraaposentadoria.feature_simulation.presentation.input_screen.InputScreen
import com.akva.calculadoraaposentadoria.feature_simulation.presentation.understand_simulation_screen.UnderstandSimulationScreen
import com.akva.calculadoraaposentadoria.navigation.Screens.*

@RequiresApi(33)
@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = InputScreen.route) {
            InputScreen(
                onNavigationToDetails = { simulationParameters: String ->
                    val routeWithArgs =
                        DetailsScreen.passSimulationParameters(simulationParameters)
                    navController.navigate(routeWithArgs)
                },
                navigateToUnderstandSimulation = { navController.navigate(UnderstandSimulationScreen.route) }
            )
        }

        composable(
            route = DetailsScreen.route, arguments = listOf(
                navArgument(SIMULATION_PARAMETERS_ARGS_KEY) {
                    type = SimulationParameters.NavigationType
                }
            )
        ) { backStackEntry ->
            val simulationParameters =
                backStackEntry.arguments?.getParcelable<SimulationParameters>(
                    SIMULATION_PARAMETERS_ARGS_KEY
                )
            DetailsScreen(
                detailsScreenViewModel = viewModel(backStackEntry),
                simulationParameters = simulationParameters,
                popBackStack = { navController.popBackStack() },
                navigateToYearByYear = { navController.navigate(YearByYearScreen.route) }
            )
        }

        composable(route = UnderstandSimulationScreen.route) {
            UnderstandSimulationScreen(popBackStack = { navController.popBackStack() })
        }

        composable(route = YearByYearScreen.route) {
            val viewModelStoreOwner =
                remember { navController.getBackStackEntry(DetailsScreen.route) }
            YearByYearScreen(
                detailsScreenViewModel = viewModel(viewModelStoreOwner),
                popBackStack = { navController.popBackStack() })
        }
    }

}