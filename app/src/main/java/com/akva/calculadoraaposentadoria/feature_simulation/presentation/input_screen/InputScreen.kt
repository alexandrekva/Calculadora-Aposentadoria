package com.akva.calculadoraaposentadoria.feature_simulation.presentation.input_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akva.calculadoraaposentadoria.R
import com.akva.calculadoraaposentadoria.core.components.decimal_value_input_field.DecimalValueInputField
import com.akva.calculadoraaposentadoria.core.components.labeled_slider.LabeledSlider
import com.akva.calculadoraaposentadoria.core.components.labeled_switch.LabeledSwitch
import com.akva.calculadoraaposentadoria.feature_simulation.presentation.components.InfoIconWithDialog

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun InputScreen(
    inputScreenViewModel: InputScreenViewModel = viewModel(),
    onNavigationToDetails: (String) -> Unit,
    navigateToUnderstandSimulation: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val dialogState = inputScreenViewModel.dialogState
    val menuState = inputScreenViewModel.menuState
    val appBarState = rememberTopAppBarState()
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior(appBarState) }


    val years = inputScreenViewModel.years
    val initialAmount = inputScreenViewModel.initialAmount
    val monthlyContribution = inputScreenViewModel.monthlyContribution
    val monthlyAppreciation = inputScreenViewModel.monthlyAppreciation
    val monthlyYield = inputScreenViewModel.monthlyYield
    val isReinvestingDividends = inputScreenViewModel.isReinvestingDividends

    when (val dialogStateValue = dialogState.value) {
        is DialogState.Closed -> {}
        is DialogState.Open -> {
            dialogStateValue.run {
                AlertDialog(
                    onDismissRequest = inputScreenViewModel::setDialogClosed,
                    confirmButton = {},
                    title = { Text(text = title) },
                    text = { Text(text = description) },
                    dismissButton = {
                        TextButton(onClick = inputScreenViewModel::setDialogClosed) {
                            Text(text = stringResource(R.string.fechar))
                        }
                    }
                )
            }
        }
    }

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(scrollBehavior.nestedScrollConnection)
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) { focusManager.clearFocus() },
        topBar = {
            SmallTopAppBar(
                title = { Text(text = stringResource(id = R.string.simulacao)) },
                scrollBehavior = scrollBehavior,
                actions = {
                    IconButton(onClick = { inputScreenViewModel.setMenuState(true) }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu")

                        DropdownMenu(
                            expanded = menuState.value,
                            onDismissRequest = { inputScreenViewModel.setMenuState(false) }
                        ) {
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.entenda_simulacao)) },
                                onClick = {
                                    navigateToUnderstandSimulation()
                                    inputScreenViewModel.setMenuState(false)
                                },
                            )
                            DropdownMenuItem(
                                text = { Text(stringResource(R.string.sobre_app)) },
                                onClick = { /* Handle edit! */ },
                            )
                        }
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1F)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LabeledSwitch(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(R.string.reinvestir_proventos),
                    isActive = isReinvestingDividends
                )

                DecimalValueInputField(
                    modifier = Modifier.fillMaxWidth(),
                    input = initialAmount,
                    label = { Text(text = stringResource(id = R.string.investimento_inicial)) },
                    leadingIcon = {
                        Text(
                            text = stringResource(R.string.simbolo_moeda),
                            fontWeight = FontWeight(600)
                        )
                    },
                    trailingIcon = {
                        InfoIconWithDialog(
                            dialogTitle = stringResource(R.string.investimento_inicial_dialog_title),
                            dialogDescription = stringResource(R.string.investimento_inicial_dialog_description),
                            dialogCall = inputScreenViewModel::setDialogOpen
                        )
                    },
                    onDoneAction = { focusManager.clearFocus() }
                )

                DecimalValueInputField(
                    modifier = Modifier.fillMaxWidth(),
                    input = monthlyContribution,
                    label = { Text(text = stringResource(id = R.string.aporte_mensal)) },
                    leadingIcon = {
                        Text(
                            text = stringResource(R.string.simbolo_moeda),
                            fontWeight = FontWeight(600)
                        )
                    },
                    trailingIcon = {
                        InfoIconWithDialog(
                            dialogTitle = stringResource(R.string.aporte_mensal_dialog_title),
                            dialogDescription = stringResource(R.string.aporte_mensal_dialog_description),
                            dialogCall = inputScreenViewModel::setDialogOpen
                        )
                    },
                    onDoneAction = { focusManager.clearFocus() }
                )

                DecimalValueInputField(
                    modifier = Modifier.fillMaxWidth(),
                    input = monthlyAppreciation,
                    label = { Text(text = stringResource(id = R.string.rendimento_mensal)) },
                    maxLength = 3,
                    leadingIcon = {
                        Text(
                            text = stringResource(R.string.simbolo_porcentagem),
                            fontWeight = FontWeight(900)
                        )
                    },
                    trailingIcon = {
                        InfoIconWithDialog(
                            dialogTitle = stringResource(R.string.rendimento_mensal_dialog_title),
                            dialogDescription = stringResource(R.string.rendimento_mensal_dialog_desciption),
                            dialogCall = inputScreenViewModel::setDialogOpen
                        )
                    },
                    onDoneAction = { focusManager.clearFocus() }
                )

                DecimalValueInputField(
                    modifier = Modifier.fillMaxWidth(),
                    input = monthlyYield,
                    label = { Text(text = stringResource(id = R.string.proventos_mensais)) },
                    maxLength = 3,
                    leadingIcon = {
                        Text(
                            text = stringResource(R.string.simbolo_porcentagem),
                            fontWeight = FontWeight(900)
                        )
                    },
                    trailingIcon = {
                        InfoIconWithDialog(
                            dialogTitle = stringResource(R.string.proventos_mensais_dialog_title),
                            dialogDescription = stringResource(R.string.proventos_mensais_dialog_description),
                            dialogCall = inputScreenViewModel::setDialogOpen
                        )
                    },
                    onDoneAction = { focusManager.clearFocus() }
                )

                LabeledSlider(
                    modifier = Modifier.fillMaxWidth(),
                    label = pluralStringResource(
                        id = R.plurals.stringPluralAnos,
                        count = years.value.toInt()
                    ),
                    progress = years
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                onClick = {
                    val simulationParameters = inputScreenViewModel.getSimulationParameters()
                    onNavigationToDetails(simulationParameters.toString())
                }) {
                Text(text = stringResource(R.string.simular))
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun PreviewInputScreen() {
    InputScreen(onNavigationToDetails = {}, navigateToUnderstandSimulation = {})
}