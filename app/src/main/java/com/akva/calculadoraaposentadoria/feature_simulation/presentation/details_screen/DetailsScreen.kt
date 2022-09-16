package com.akva.calculadoraaposentadoria.feature_simulation.presentation.details_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akva.calculadoraaposentadoria.R
import com.akva.calculadoraaposentadoria.core.components.loading_screen.LoadingScreen
import com.akva.calculadoraaposentadoria.core.extensions.toCurrencyFormat
import com.akva.calculadoraaposentadoria.core.extensions.toYearsAndMonthsString
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.SimulationDetails
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.SimulationParameters
import com.akva.calculadoraaposentadoria.feature_simulation.presentation.components.LabeledText
import com.akva.calculadoraaposentadoria.feature_simulation.presentation.components.PrimaryDetailsInfoCard
import com.akva.calculadoraaposentadoria.feature_simulation.presentation.details_screen.DetailsScreenViewState.*
import java.math.BigDecimal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    detailsScreenViewModel: DetailsScreenViewModel,
    simulationParameters: SimulationParameters?,
    popBackStack: () -> Unit,
    navigateToYearByYear: () -> Unit
) {

    val detailsScreenViewState = detailsScreenViewModel.detailsScreenViewState
    val appBarState = rememberTopAppBarState()
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior(appBarState) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SmallTopAppBar(
                title = { Text(text = stringResource(id = R.string.detalhes)) },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = popBackStack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(
                                R.string.voltar
                            )
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (val viewState = detailsScreenViewState.value) {
                is Loading -> {
                    LoadingScreen()
                    requireNotNull(simulationParameters)
                    detailsScreenViewModel.simulate(simulationParameters)
                }
                is Loaded -> DetailsSection(
                    simulationDetails = viewState.simulationDetails,
                    navigateToYearByYear = navigateToYearByYear
                )
            }
        }
    }
}

@Composable
private fun DetailsSection(
    simulationDetails: SimulationDetails,
    navigateToYearByYear: () -> Unit
) {
    simulationDetails.run {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            PrimaryDetailsInfoCard(
                simulationDetails = simulationDetails,
                onClick = navigateToYearByYear
            )
            Spacer(modifier = Modifier.height(32.dp))
            LabeledText(
                label = stringResource(R.string.proventos_mensais),
                body = stringResource(
                    R.string.proventos_mensais_ao_final,
                    finalMonthlyDividends.toCurrencyFormat()
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = if (hasDividendsSurpassedMonthlyContribution) {
                    stringResource(
                        R.string.proventos_ultrapassaram_contribuicao_mensal,
                        dividendsSurpassedMonthlyContributionMonth.toYearsAndMonthsString(
                            LocalContext.current
                        ) ?: ""
                    )
                } else {
                    stringResource(R.string.proventos_nao_ultrapassaram_contribuicao_mensal)
                },
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(32.dp))
            LabeledText(
                label = stringResource(R.string.primeiro_milhao),
                body = when (firstMillionAchievedMonth) {
                    0 -> stringResource(R.string.primeiro_milhao_ja_possuido)
                    -1 -> stringResource(R.string.primeiro_milhao_nao_alcancado)
                    else -> stringResource(
                        R.string.primeiro_milhao_alcancado,
                        firstMillionAchievedMonth.toYearsAndMonthsString(LocalContext.current)
                            ?: ""
                    )
                }
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun PreviewDetailsScreen() {
    val simulationParameters = SimulationParameters(
        initialAmount = BigDecimal(1000),
        monthlyContribution = BigDecimal(100),
        monthlyYield = 0.05f,
        monthlyAppreciation = 0.07f,
        years = 10,
        isReinvestingDividends = true
    )

    DetailsScreen(
        detailsScreenViewModel = viewModel(),
        simulationParameters = simulationParameters,
        popBackStack = {},
        navigateToYearByYear = {},
    )
}