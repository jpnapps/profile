package com.jpn.core.ui.dialogs.secure

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun SecureEntryScreen(
    onAuthSuccess: () -> Unit,
    onDismiss: () -> Unit,
) {
    var showSecureDialog by remember { mutableStateOf(true) }
    var showPinDialog by remember { mutableStateOf(false) }



    if (showSecureDialog) {
        SecureAccessDialog(
            onAuthSuccess = {
                onAuthSuccess()
              /* navController navController.navigate("note_detail/$noteId") {
                    popUpTo("secure_entry/$noteId") { inclusive = true }
                }*/
            },
            onPinOptionSelected = {
                showSecureDialog = false
                showPinDialog = true
            },
            onDismiss = {
                onDismiss()
               // navController.popBackStack() // cancel access
            }
        )
    }

    if (showPinDialog) {
        PinEntryDialog(
            onPinEntered = { pin ->
                if (pin == "1234") { // Replace with real PIN validation logic
                   /* navController.navigate("note_detail/$noteId") {
                        popUpTo("secure_entry/$noteId") { inclusive = true }
                    }*/
                    onAuthSuccess()
                } else {
                   // Toast.makeText(LocalContext.current, "Invalid PIN", Toast.LENGTH_SHORT).show()
                }
            },
            onDismiss = {
                //navController.popBackStack()
                onDismiss()
            }
        )
    }
}
