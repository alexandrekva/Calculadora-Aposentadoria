package com.akva.calculadoraaposentadoria.feature_simulation.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LabeledText(
    modifier: Modifier = Modifier,
    label: String,
    body: String
) {
    Column(modifier = modifier) {
        Text(text = label, style = MaterialTheme.typography.labelLarge)
        Text(
            text = body,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun PreviewLabeledText() {
    LabeledText(label = "Label", body = "Body")
}