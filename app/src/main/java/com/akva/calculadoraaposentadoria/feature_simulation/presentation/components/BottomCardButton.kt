package com.akva.calculadoraaposentadoria.feature_simulation.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomCardButton(
    modifier: Modifier = Modifier,
    label: String,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topEnd = 0.dp,
                    topStart = 0.dp,
                    bottomStart = 12.dp,
                    bottomEnd = 12.dp
                )
            )
            .clickable { onClick() }
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = label, style = MaterialTheme.typography.titleMedium)
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = imageVector,
                contentDescription = null
            )
        }
    }

}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)

private fun PreviewBottomCardButton() {
    Column() {
        BottomCardButton(
            modifier = Modifier.fillMaxWidth(),
            label = "Teste",
            imageVector = Icons.Default.KeyboardArrowRight,
            onClick = {}
        )
    }
}