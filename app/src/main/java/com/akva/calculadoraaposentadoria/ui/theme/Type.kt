package com.akva.calculadoraaposentadoria.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

@Preview(showBackground = true)
@Composable
fun PreviewTypography() {
    Column() {
        Text(text = "display large", style = MaterialTheme.typography.displayLarge)
        Text(text = "display medium", style = MaterialTheme.typography.displayMedium)
        Text(text = "display small", style = MaterialTheme.typography.displaySmall)
        Text(text = "headline large", style = MaterialTheme.typography.headlineLarge)
        Text(text = "headline medium", style = MaterialTheme.typography.headlineMedium)
        Text(text = "headline small", style = MaterialTheme.typography.headlineSmall)
        Text(text = "title large", style = MaterialTheme.typography.titleLarge)
        Text(text = "title medium", style = MaterialTheme.typography.titleMedium)
        Text(text = "title small", style = MaterialTheme.typography.titleSmall)
        Text(text = "label large", style = MaterialTheme.typography.labelLarge)
        Text(text = "label medium", style = MaterialTheme.typography.labelMedium)
        Text(text = "label small", style = MaterialTheme.typography.labelSmall)
        Text(text = "body large", style = MaterialTheme.typography.bodyLarge)
        Text(text = "body medium", style = MaterialTheme.typography.bodyMedium)
        Text(text = "body small", style = MaterialTheme.typography.bodySmall)
    }
}