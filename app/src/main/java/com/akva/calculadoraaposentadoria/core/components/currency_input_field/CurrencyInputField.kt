package com.akva.calculadoraaposentadoria.core.components.currency_input_field

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyInputField(
    modifier: Modifier = Modifier,
    input: MutableState<String>,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    maxLength: Int = 12,
) {
    OutlinedTextField(
        modifier = modifier,
        textStyle = TextStyle(baselineShift = BaselineShift(-0.2f)),
        value = input.value,
        onValueChange = {
            if (it.length <= maxLength) {
                input.value = it
            }
        },
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        visualTransformation = CurrencyInputVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
    )
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun PreviewCurrencyInputField() {
    Column(modifier = Modifier.padding(24.dp)) {
        CurrencyInputField(input = remember { mutableStateOf("0") })
    }
}