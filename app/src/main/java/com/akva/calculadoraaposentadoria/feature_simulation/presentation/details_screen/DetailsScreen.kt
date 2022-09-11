package com.akva.calculadoraaposentadoria.feature_simulation.presentation.details_screen

import androidx.compose.foundation.layout.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    detailsScreenViewModel: DetailsScreenViewModel = viewModel(),
    simulationParameters: SimulationParameters?,
    popBackStack: () -> Unit
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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (val viewState = detailsScreenViewState.value) {
                is DetailsScreenViewState.Loading -> {
                    LoadingScreen()
                    requireNotNull(simulationParameters)
                    detailsScreenViewModel.simulate(simulationParameters)
                }
                is DetailsScreenViewState.Loaded -> DetailsSection(viewState.simulationDetails)
            }
        }
    }
}

@Composable
private fun DetailsSection(simulationDetails: SimulationDetails) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        simulationDetails.run {
            LabeledText(
                label = stringResource(R.string.patrimonio_total),
                body = stringResource(
                    R.string.simbolo_moeda_com_texto,
                    totalPatrimony.toCurrencyFormat()
                )
            )
            LabeledText(
                label = stringResource(R.string.total_investido),
                body = stringResource(
                    R.string.simbolo_moeda_com_texto,
                    totalInvested.toCurrencyFormat()
                )
            )
            LabeledText(
                label = stringResource(R.string.total_reinvestido_dividendos),
                body = stringResource(
                    R.string.simbolo_moeda_com_texto,
                    totalReinvestedDividends.toCurrencyFormat()
                )
            )
            LabeledText(
                label = stringResource(R.string.valorizacao_total),
                body = stringResource(
                    R.string.simbolo_moeda_com_texto,
                    totalAppreciation.toCurrencyFormat()
                )
            )

            Divider(modifier = Modifier.fillMaxWidth())

            Text(
                text = stringResource(
                    R.string.proventos_mensais_ao_final,
                    finalMonthlyDividends.toCurrencyFormat()
                ),
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = when (firstMillionAchievedMonth) {
                    0 -> stringResource(R.string.primeiro_milhao_ja_possuido)
                    -1 -> stringResource(R.string.primeiro_milhao_nao_alcancado)
                    else -> stringResource(
                        R.string.primeiro_milhao_alcancado,
                        firstMillionAchievedMonth.toYearsAndMonthsString(LocalContext.current) ?: ""
                    )
                },
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = if (hasDividendsSurpassedMonthlyContribution) {
                    stringResource(
                        R.string.proventos_ultrapassaram_contribuicao_mensal,
                        dividendsSurpassedMonthlyContributionMonth.toYearsAndMonthsString(LocalContext.current) ?: ""
                    )
                } else {
                    stringResource(R.string.proventos_nao_ultrapassaram_contribuicao_mensal)
                },
                style = MaterialTheme.typography.bodyLarge
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
    DetailsScreen(simulationParameters = null, popBackStack = {})
}