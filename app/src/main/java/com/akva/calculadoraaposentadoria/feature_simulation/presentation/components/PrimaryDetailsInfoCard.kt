package com.akva.calculadoraaposentadoria.feature_simulation.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akva.calculadoraaposentadoria.R
import com.akva.calculadoraaposentadoria.core.extensions.toCurrencyFormat
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.SimulationDetails

@Composable
fun PrimaryDetailsInfoCard(
    modifier: Modifier = Modifier,
    simulationDetails: SimulationDetails,
    onClick: () -> Unit
) {
    Card(modifier = modifier) {
        simulationDetails.run {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
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
                    label = if (simulationParameters.isReinvestingDividends) stringResource(R.string.total_proventos_reinvestido)
                    else stringResource(R.string.total_proventos),
                    body = stringResource(
                        R.string.simbolo_moeda_com_texto,
                        totalDividends.toCurrencyFormat(),
                    )
                )

                LabeledText(
                    label = stringResource(R.string.valorizacao_total),
                    body = stringResource(
                        R.string.simbolo_moeda_com_texto,
                        totalAppreciation.toCurrencyFormat()
                    )
                )
            }
            Divider()
            BottomCardButton(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                label = stringResource(R.string.acompanhe_ano_ano),
                imageVector = Icons.Rounded.KeyboardArrowRight,
                onClick = onClick
            )
        }
    }
}

@Composable
@Preview(
    showSystemUi = true,
    showBackground = true
)
private fun PreviewPrimaryDetailsInfoCard() {
    Column {
        // PrimaryDetailsInfoCard()
    }
}