package com.akva.calculadoraaposentadoria.feature_simulation.presentation.input_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.akva.calculadoraaposentadoria.R
import com.akva.calculadoraaposentadoria.core.components.currency_input_field.CurrencyInputField
import com.akva.calculadoraaposentadoria.core.components.labeled_slider.LabeledSlider
import com.akva.calculadoraaposentadoria.core.components.labeled_switch.LabeledSwitch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(
    inputScreenViewModel: InputScreenViewModel = viewModel(),
    onNavigationToDetails: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val years = inputScreenViewModel.years
    val initialAmount = inputScreenViewModel.initialAmount
    val monthlyContribution = inputScreenViewModel.monthlyContribution
    val isReinvestingDividends = inputScreenViewModel.isReinvestingDividends

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) { focusManager.clearFocus() },
        topBar = {
            Text(
                text = "Simulação",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LabeledSwitch(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(R.string.reinvest_dividends),
                    isActive = isReinvestingDividends
                )

                CurrencyInputField(
                    modifier = Modifier.fillMaxWidth(),
                    input = initialAmount,
                    leadingIcon = {
                        Text(text = "R$", fontWeight = FontWeight(600))
                    }
                )

                CurrencyInputField(
                    modifier = Modifier.fillMaxWidth(),
                    input = monthlyContribution,
                    maxLength = 3
                )

                LabeledSlider(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(R.string.years),
                    progress = years
                )
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
    InputScreen(onNavigationToDetails = {})
}