package com.jpn.core.ui.inputs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun FourDigitPinInput(
    pinValue: String,
    onPinValueChange: (String) -> Unit,
    hasError: Boolean,
    modifier: Modifier = Modifier,
    errorMessage: String? = null
) {
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { focusRequester.requestFocus() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Hidden field to capture input
        BasicTextField(
            value = pinValue,
            onValueChange = {
                if (it.length <= 4 && it.all { char -> char.isDigit() }) {
                    onPinValueChange(it)
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .size(0.dp) // hide actual field
                .focusRequester(focusRequester)
                .focusable()
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(4) { index ->
                val char = pinValue.getOrNull(index)?.toString() ?: ""
                PinBox(digit = char, showError = hasError)
            }
        }

        // Optional error message display
        if (hasError && !errorMessage.isNullOrBlank()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}


@Composable
fun PinBox(
    digit: String,
    showError: Boolean
) {
    val borderColor = if (showError) Color.Red else Color.Gray
    Box(
        modifier = Modifier
            .size(48.dp)
            .border(2.dp, borderColor, RoundedCornerShape(8.dp))
            .background(Color.White)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = digit,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
    }
}
