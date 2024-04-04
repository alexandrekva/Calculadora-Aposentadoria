package com.akva.calculadoraaposentadoria.core.components.decimal_value_input_field

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.akva.calculadoraaposentadoria.core.extensions.toCurrencyFormat

class DecimalValueInputVisualTransformation() :
    VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = text.text.toCurrencyFormat()

        val newText = AnnotatedString(
            text = formattedText,
            spanStyles = text.spanStyles,
            paragraphStyles = text.paragraphStyles
        )

        val offsetMapping = FixedCursorOffsetMapping(
            contentLength = text.length,
            formattedContentLength = formattedText.length
        )

        return TransformedText(text = newText, offsetMapping = offsetMapping)
    }

    private class FixedCursorOffsetMapping(
        private val contentLength: Int,
        private val formattedContentLength: Int,
    ) : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return formattedContentLength
        }

        override fun transformedToOriginal(offset: Int): Int {
            return contentLength
        }
    }
}