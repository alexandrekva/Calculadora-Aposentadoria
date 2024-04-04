package com.akva.calculadoraaposentadoria.feature_simulation.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.akva.calculadoraaposentadoria.R

@Composable
fun InfoIconWithDialog(
    dialogTitle: String,
    dialogDescription: String,
    dialogCall: (dialogTitle: String, dialogDescription: String) -> Unit,
    tint: Color = MaterialTheme.colorScheme.outline
) {
    IconButton(onClick = {
        dialogCall(dialogTitle, dialogDescription)
    }) {
        Icon(
            imageVector = Icons.Outlined.Info,
            tint = tint,
            contentDescription = stringResource(R.string.icone_informacao)
        )
    }
}