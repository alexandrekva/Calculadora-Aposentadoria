package com.akva.calculadoraaposentadoria.core.components.labeled_slider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LabeledSlider(
    modifier: Modifier = Modifier,
    label: String,
    progress: MutableState<Float>,
    valueRange: ClosedFloatingPointRange<Float> = 0f..60f
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(0.20f),
            style = MaterialTheme.typography.labelLarge,
            text = "${progress.value.toInt()} $label"
        )
        Slider(
            modifier = Modifier.fillMaxWidth(0.80f),
            value = progress.value,
            valueRange = valueRange,
            onValueChange = { if (it.toInt() != 0) progress.value = it })
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun PreviewLabeledSlider() {
    LabeledSlider(
        label = "anos",
        progress = remember { mutableStateOf(0f) }
    )
}