package com.akva.calculadoraaposentadoria.core.components.labeled_switch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LabeledSwitch(
    modifier: Modifier = Modifier,
    label: String,
    isActive: MutableState<Boolean>

) {

    val icon: @Composable (() -> Unit)? = if (isActive.value) {
        {
            Icon(
                imageVector = Icons.Rounded.Check,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp)
            )
        }
    } else {
        null
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(style = MaterialTheme.typography.labelLarge, text = label)
        Switch(
            checked = isActive.value,
            onCheckedChange = { isActive.value = it },
            thumbContent = icon
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun PreviewLabeledSwitch() {
    LabeledSwitch(label = "Reinvestir dividendos?", isActive = remember { mutableStateOf(true)})
}