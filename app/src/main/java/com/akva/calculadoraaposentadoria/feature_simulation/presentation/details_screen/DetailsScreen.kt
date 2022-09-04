package com.akva.calculadoraaposentadoria.feature_simulation.presentation.details_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akva.calculadoraaposentadoria.R
import com.akva.calculadoraaposentadoria.core.components.loading_screen.LoadingScreen
import com.akva.calculadoraaposentadoria.core.extensions.toYearsAndMonthsString
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.SimulationDetails
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.SimulationParameters
import com.akva.calculadoraaposentadoria.feature_simulation.presentation.components.LabeledText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    detailsScreenViewModel: DetailsScreenViewModel = viewModel(),
    simulationParameters: SimulationParameters?
) {

    val detailsScreenViewState = detailsScreenViewModel.detailsScreenViewState

    Scaffold(
        topBar = {
            Text(
                text = "Detalhes",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
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
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        simulationDetails.run {
            LabeledText(
                label = stringResource(R.string.patrimonio_total),
                body = stringResource(R.string.simbolo_moeda_com_texto, totalPatrimonyString)
            )
            LabeledText(
                label = stringResource(R.string.total_investido),
                body = stringResource(R.string.simbolo_moeda_com_texto, totalInvestedString)
            )
            LabeledText(
                label = stringResource(R.string.total_reinvestido_dividendos),
                body = stringResource(R.string.simbolo_moeda_com_texto, totalReinvestedDividendsString)
            )
            LabeledText(
                label = stringResource(R.string.valorizacao_total),
                body = stringResource(R.string.simbolo_moeda_com_texto, totalAppreciationString)
            )
            LabeledText(
                label = stringResource(R.string.primeiro_milhao),
                body = when (firstMillionAchievedMonth) {
                    0 -> stringResource(R.string.primeiro_milhao_ja_possuido)
                    -1 -> stringResource(R.string.primeiro_milhao_nao_alcancado)
                    else -> stringResource(
                        R.string.primeiro_milhao_alcancado,
                        firstMillionAchievedMonth.toYearsAndMonthsString()!!
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
    DetailsScreen(simulationParameters = null)
}