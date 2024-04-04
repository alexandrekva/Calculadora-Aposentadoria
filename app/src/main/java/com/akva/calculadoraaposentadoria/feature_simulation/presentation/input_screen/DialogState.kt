package com.akva.calculadoraaposentadoria.feature_simulation.presentation.input_screen

sealed class DialogState {
    object Closed : DialogState()
    data class Open(val title: String, val description: String) : DialogState()
}