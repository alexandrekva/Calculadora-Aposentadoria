package com.akva.calculadoraaposentadoria.feature_simulation.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                    label = stringResource(R.string.total_reinvestido_proventos),
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
            }
            Divider()
            BottomCardButton(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                label = "Acompanhe ano a ano",
                imageVector = Icons.Rounded.KeyboardArrowRight,
                onClick = onClick)
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