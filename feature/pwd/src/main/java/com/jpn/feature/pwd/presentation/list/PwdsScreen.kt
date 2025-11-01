package com.jpn.feature.pwd.presentation.list

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.jpn.core.ui.box.BoxGreyBorder
import com.jpn.core.ui.topbars.ListScreenTopBar
import com.jpn.domain.model.PwdData

@Composable
fun PwdsScreen(
    viewModel: PwdsViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit,
    onAddClick: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val pwdUiModels by viewModel.pwdUiModels.collectAsState()
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var toastMessage by remember { mutableStateOf<String?>(null) }
    var showSecureEntry by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<PwdData?>(null) }
    var selectedItemId by remember { mutableStateOf<Int>(-1) }

    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is UiEvent.CopytoClip -> {
                        val clipboard =
                            context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
                        if (clipboard != null) {
                            clipboard.setPrimaryClip(
                                ClipData.newPlainText("PWD Backup", event.json)
                            )
                            toastMessage = "Passwords copied to clipboard"
                        } else {
                            toastMessage = "Clipboard service unavailable"
                        }
                    }

                    is UiEvent.Error -> {
                        errorMessage = event.message
                    }

                    is UiEvent.ShowToast -> {
                        toastMessage = event.message
                    }

                    else -> {

                    }
                }
            }
        }
    }

    toastMessage?.let {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }

    Scaffold(
        topBar = {
            ListScreenTopBar(
                title = "Key Manager",
                isExportEnabled = true,
                contentDescription = "Key Manager",
                onImport = { },
                onExport = { viewModel.exportPwds() },
                onAdd = onAddClick,
            )

        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Add Password")
            }
        }) { padding ->

        LazyColumn(modifier = Modifier.padding(padding)) {
            items(pwdUiModels.size) { index ->
                val item = pwdUiModels.get(index)
                BoxGreyBorder(onClick = {
                    showSecureEntry = true
                    selectedItemId = item.id
                }) {
                    Column {
                        if (item.label.isNotBlank())
                            Text(
                                text = item.label,
                                style = MaterialTheme.typography.labelLarge
                            )
                        Spacer(modifier = Modifier.height(4.dp))
                        if (item.secureData.isNotBlank())
                            Text(
                                text = item.secureData,
                                style = MaterialTheme.typography.bodyMedium
                            )
                    }
                }
            }
        }

        if (showSecureEntry && selectedItemId >= 0) {
            onItemClick(selectedItemId)
            /*SecureEntryDialog(onAuthSuccess = {
                showSecureEntry = false
                onItemClick(selectedItemId)
            }) {
                showSecureEntry = false
            }*/
        }
    }
}