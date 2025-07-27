package com.jpn.core.ui.dialogs.secure

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jpn.core.ui.inputs.SecurePinInput


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecureEntryDialog(
    onAuthSuccess: () -> Unit,
    onDismiss: () -> Unit,
) {
    var pin by remember { mutableStateOf("") }
    var pinError by remember { mutableStateOf(false) }
    var launchBiometric by remember { mutableStateOf(false) }

    BasicAlertDialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 6.dp,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(32.dp)) {
                Text(
                    text = "Unlock",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Use fingerprint or enter PIN ",modifier = Modifier.align(Alignment.CenterHorizontally))
                Spacer(modifier = Modifier.height(16.dp))

                SecurePinInput(
                    pinLength = 4,
                    onComplete = { pin = it
                        if (it.length == 4 && it == "1234") {
                            pinError=false
                        onAuthSuccess()
                    }
                    else{
                      pinError =true
                    }
                                       },
                    hasError = pinError,
                    errorMessage = "Invalid PIN. Try again."
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(onClick = { launchBiometric = true }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text("Use FingerPrint")
                }
            }
        }
    }

    if (launchBiometric) {
        LaunchBiometricPrompt(
            true,
            onAuthSuccess = { onAuthSuccess() },
            onAuthFailed = {},
            onCancel = { onDismiss() })

    }
}