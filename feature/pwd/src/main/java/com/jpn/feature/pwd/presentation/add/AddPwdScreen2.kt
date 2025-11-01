package com.jpn.feature.pwd.presentation.add

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.jpn.core.ui.cards.WhiteCard
import com.jpn.core.ui.containers.ScreenContainer
import com.jpn.core.ui.dialogs.ErrorDialog
import com.jpn.core.ui.dialogs.LoadingDialog
import com.jpn.core.ui.dialogs.NoNetworkDialog
import com.jpn.core.ui.dialogs.SuccessDialog
import com.jpn.core.ui.inputs.DeleteableLockTextField
import com.jpn.core.ui.topbars.AddScreenTopBar
import com.jpn.feature.pwd.R

@Composable
fun AddPwdScreen2(
    viewModel: AddPwdViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {

    val form = viewModel.formState
    val lifecycleOwner = LocalLifecycleOwner.current

    var showDialog by remember { mutableStateOf(false) }
    var showNoNetworkDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val uiState by viewModel.uiState.collectAsState()
    val fieldLabel by viewModel.fieldLabel.collectAsState()
    val isFieldEnabled by viewModel.isAddFieldEnabled.collectAsState()
    val isSecureEnabled by viewModel.isAddSecureEnabled.collectAsState()
    val isSaveEnabled by viewModel.isSaveEnabled.collectAsState()

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
                // navigate back
                onBackClick()
            }
        )
    }

    if (showNoNetworkDialog) {
        NoNetworkDialog(onRetry = {}) { }
    }

    errorMessage?.let {
        ErrorDialog(it) {
            errorMessage = null
            viewModel.onFieldDelete(false)
        }
    }

    if (uiState == AddPwdUiState.Loading)
        LoadingDialog()

    Scaffold(
        topBar = {
            AddScreenTopBar(
                title = form.title,
                isSaveEnabled = true,
                isAddFieldEnabled = isFieldEnabled,
                isAddSecureEnabled = isSecureEnabled,
                contentDescription = form.title,
                onBackClick = onBackClick,
                onSaveClick = { viewModel.onSaveClick() },
                onAddField = { viewModel.onFieldDelete(false) },
                onAddSecure = { viewModel.onSecureDelete(false) })

        }
    ) { innerPadding ->
        ScreenContainer(innerPadding) {

            WhiteCard {
                if (!form.isFieldDeleted)
                    DeleteableLockTextField(
                        label = fieldLabel,
                        onLabelChange = { viewModel.onFieldLabelChange(it) },
                        labelError = form.errorFieldLabel,
                        contentValue = form.fieldValue,
                        onContentValueChange = { viewModel.onFieldValueChange(it) },
                        contentLabel = fieldLabel,
                        contentError = form.errorFieldValue,
                        contentHint = stringResource(R.string.enter_field, fieldLabel),
                        isLocked = form.fieldLock,
                        borderColor = Color.Blue,
                        onLockToggle = {
                            viewModel.toggleFieldLock()
                        }) {
                        viewModel.onFieldDelete(true)
                    }
                Spacer(modifier = Modifier.height(16.dp))

                if (!form.isSecureDeleted)
                    DeleteableLockTextField(
                        label = form.secureLabel,
                        onLabelChange = { viewModel.onSecureLabelChange(it) },
                        labelError = form.errorSecureLabel,
                        contentValue = form.secureValue,
                        onContentValueChange = { viewModel.onSecureValueChange(it) },
                        contentLabel = form.secureLabel,
                        contentError = form.errorSecureValue,
                        contentHint = stringResource(R.string.enter_field, form.secureLabel),
                        isLocked = form.secureLock,
                        borderColor = Color.Red,
                        onLockToggle = {
                            viewModel.toggleSecureLock()
                        }) {
                        viewModel.onSecureDelete(true)
                    }

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
    }

}

