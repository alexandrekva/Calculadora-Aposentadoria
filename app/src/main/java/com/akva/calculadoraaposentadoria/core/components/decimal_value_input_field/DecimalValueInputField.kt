package com.akva.calculadoraaposentadoria.core.components.decimal_value_input_field

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
fun DecimalValueInputField(
    modifier: Modifier = Modifier,
    input: MutableState<String>,
    label: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    maxLength: Int = 12,
    onDoneAction: (KeyboardActionScope.() -> Unit)? = null
) {
    OutlinedTextField(
        modifier = modifier,
        textStyle = TextStyle(baselineShift = BaselineShift(-0.2f)),
        value = input.value,
        onValueChange = {
            if (it.length <= maxLength && it != "0") {
                input.value = it
            }
        },
        label = label,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        visualTransformation = DecimalValueInputVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        keyboardActions = KeyboardActions(onDone = onDoneAction),
        singleLine = true
    )
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun PreviewDecimalValueInputField() {
    Column(modifier = Modifier.padding(24.dp)) {
        DecimalValueInputField(
            modifier = Modifier.fillMaxWidth(),
            input = remember { mutableStateOf("0") },
            label = { Text(text = "Title") },
            leadingIcon = { Text(text = "R$") },
            trailingIcon = { Icon(imageVector = Icons.Outlined.Info, contentDescription = null) }
        )
    }
}