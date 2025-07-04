package com.jpn.feature.notes.presentation.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.jpn.core.ui.dialogs.ErrorDialog
import com.jpn.core.ui.dialogs.LoadingDialog
import com.jpn.core.ui.dialogs.NoNetworkDialog
import com.jpn.core.ui.dialogs.SuccessDialog
import com.jpn.feature.pwd.R

@Composable
fun AddPwdScreen(
    viewModel: AddPwdViewModel = hiltViewModel(),
    onNoteSaved: () -> Unit
) {

    val form = viewModel.formState
    val lifecycleOwner = LocalLifecycleOwner.current

    var showDialog by remember { mutableStateOf(false) }
    var showNoNetworkDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.eventFlow.collect { event ->
                when (event) {
                    is AddPwdEvent.SaveSuccess -> {
                        showDialog = true
                    }

                    is AddPwdEvent.Error -> {
                        errorMessage = event.message
                    }

                    is AddPwdEvent.NoNetwork -> {
                        showNoNetworkDialog = true
                    }

                    else -> {

                    }
                }
            }
        }
    }

    if (showDialog) {
        SuccessDialog(
            message = " Added successfully!",
            onDismiss = {
                showDialog = false
                onNoteSaved() // navigate back
            }
        )
    }

    if (showNoNetworkDialog) {
        NoNetworkDialog(onRetry = {}) { }
    }

    errorMessage?.let {
        ErrorDialog(it) {}
    }

    if (uiState == AddPwdUiState.Loading)
        LoadingDialog()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.add_pwd_title),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = form.key,
            onValueChange = { viewModel.onKeyChange(it) },
            label = { Text(stringResource(R.string.pwd_key_label)) },
            isError = form.keyError != null,
            supportingText = {
                form.keyError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = form.value,
            onValueChange = viewModel::onValueChange,
            label = { Text(stringResource(R.string.pwd_password_label)) },
            isError = form.valueError != null,
            supportingText = {
                form.valueError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))


        OthersInput(
            refLinks = viewModel.formState.others,
            onAddLink = viewModel::addOther,
            onRemoveLink = viewModel::removeOther
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.onSaveClick()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(stringResource(R.string.save))
        }
    }
}

