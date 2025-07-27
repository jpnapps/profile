package com.jpn.core.ui.dialogs.secure

import androidx.compose.runtime.Composable

@Composable
fun SecureAccessDialog(
    onAuthSuccess: () -> Unit,
    onPinOptionSelected: () -> Unit,
    onDismiss: () -> Unit
) {
   /* val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val activity = context as? FragmentActivity
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Secure Access") },
        text = { Text("Use your fingerprint or enter app PIN to unlock the note") },
        confirmButton = {
            TextButton(onClick = {
                val executor = ContextCompat.getMainExecutor(context)
                val biometricPrompt = BiometricPrompt(
                    activity,
                    executor,
                    object : BiometricPrompt.AuthenticationCallback() {
                        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                            onAuthSuccess()
                        }

                        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {}
                        override fun onAuthenticationFailed() {}
                    })

                val promptInfo = BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Fingerprint Login")
                    .setNegativeButtonText("Cancel")
                    .build()

                biometricPrompt.authenticate(promptInfo)
            }) {
                Text("Use Fingerprint")
            }
        },
        dismissButton = {
            TextButton(onClick = onPinOptionSelected) {
                Text("Use PIN")
            }
        }
    )*/
}
