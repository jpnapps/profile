package com.jpn.core.ui.inputs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SecurePinInput(
    pinLength: Int = 4,
    onComplete: (String) -> Unit,
    hasError: Boolean = false,
    errorMessage: String? = null,
    modifier: Modifier = Modifier
) {
    val focusRequesters = remember { List(pinLength) { FocusRequester() } }
    val pinValues = remember { mutableStateListOf(*Array(pinLength) { "" }) }
    var passwordVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        focusRequesters.first().requestFocus()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp,Alignment.CenterHorizontally)) {
            repeat(pinLength) { index ->
                OutlinedTextField(
                    value = pinValues[index],
                    onValueChange = { value ->
                        if (value.length <= 1 && value.all { it.isDigit() }) {
                            pinValues[index] = value
                            if (value.isNotEmpty()) {
                                if (index < pinLength - 1) {
                                    focusRequesters[index + 1].requestFocus()
                                } else if (pinValues.all { it.isNotEmpty() }) {
                                    onComplete(pinValues.joinToString(""))
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .width(50.dp)
                        .focusRequester(focusRequesters[index])
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.type == KeyEventType.KeyDown &&
                                keyEvent.key == Key.Backspace &&
                                pinValues[index].isEmpty()
                            ) {
                                if (index > 0) {
                                    pinValues[index - 1] = ""
                                    focusRequesters[index - 1].requestFocus()
                                }
                                true
                            } else {
                                false
                            }
                        },
                    isError = hasError,
                    textStyle = LocalTextStyle.current.copy(
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    singleLine = true ,
                    visualTransformation = if (passwordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation()

                )
            }
        }

        // Show / Hide Toggle
        IconButton(
            onClick = { passwordVisible = !passwordVisible },
            modifier = Modifier.padding(top = 8.dp).align(Alignment.End)
        ) {
            Icon(
                imageVector = if (passwordVisible)
                    Icons.Default.Visibility
                else
                    Icons.Default.VisibilityOff,
                contentDescription = if (passwordVisible) "Hide PIN" else "Show PIN"
            )
        }

        if (hasError && !errorMessage.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

