package com.jpn.core.ui.dialogs.secure

import androidx.biometric.BiometricPrompt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

@Composable
fun LaunchBiometricPrompt(
    launchAuth: Boolean,
    onAuthSuccess: () -> Unit,
    onAuthFailed: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    val context = LocalContext.current
    val activity = context as? FragmentActivity ?: return
    val executor = ContextCompat.getMainExecutor(context)

    if (launchAuth) {
        LaunchedEffect(Unit) {
            val biometricPrompt = BiometricPrompt(
                activity,
                executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        onAuthSuccess()
                    }

                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON ||
                            errorCode == BiometricPrompt.ERROR_USER_CANCELED
                        ) {
                            onCancel()
                        } else {
                            onAuthFailed()
                        }
                    }

                    override fun onAuthenticationFailed() {
                        onAuthFailed()
                    }
                }
            )

            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Fingerprint Login")
                .setDescription("Authenticate using your biometric credential")
                .setNegativeButtonText("Cancel")
                .build()

            biometricPrompt.authenticate(promptInfo)
        }
    }
}
