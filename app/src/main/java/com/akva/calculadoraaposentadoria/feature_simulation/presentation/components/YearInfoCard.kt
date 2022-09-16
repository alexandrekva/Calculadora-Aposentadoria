package com.akva.calculadoraaposentadoria.feature_simulation.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.akva.calculadoraaposentadoria.R
import com.akva.calculadoraaposentadoria.core.extensions.toCurrencyFormat
import com.akva.calculadoraaposentadoria.feature_simulation.domain.entities.YearEvolution

@Composable
fun YearInfoCard(
    modifier: Modifier = Modifier,
    yearEvolution: YearEvolution,
) {
    var expanded by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (expanded) 180f else 0f)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
    ) {
        yearEvolution.run {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = year.toString(), style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stringResource(
                                R.string.simbolo_moeda_com_texto,
                                totalPatrimony.toCurrencyFormat(),
                            ),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    IconButton(
                        modifier = Modifier.rotate(rotationState),
                        onClick = { expanded = !expanded }) {
                        Icon(
                            modifier = Modifier.size(36.dp),
                            imageVector = Icons.Rounded.ArrowDropDown,
                            contentDescription = null
                        )
                    }
                }
                if (expanded) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider()
                    Column(
                        modifier = Modifier.padding(top = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        LabeledText(
                            label = stringResource(R.string.total_investido_ano),
                            body = stringResource(
                                R.string.simbolo_moeda_com_texto,
                                totalInvestedInYear.toCurrencyFormat()
                            )
                        )

                        LabeledText(
                            label = stringResource(R.string.total_proventos_ano),
                            body = stringResource(
                                R.string.simbolo_moeda_com_texto,
                                totalDividendsInYear.toCurrencyFormat()
                            )
                        )

                        LabeledText(
                            label = stringResource(R.string.valorizacao_total_ano),
                            body = stringResource(
                                id = R.string.simbolo_moeda_com_texto,
                                totalAppreciationInYear.toCurrencyFormat()
                            )
                        )
                    }
                }
            }
        }
    }
}