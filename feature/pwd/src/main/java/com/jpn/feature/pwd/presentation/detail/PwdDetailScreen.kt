package com.jpn.feature.pwd.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.jpn.feature.notes.presentation.add.AddPwdEvent
import com.jpn.feature.notes.presentation.add.AddPwdUiState
import com.jpn.feature.notes.presentation.add.OthersInput
import com.jpn.feature.pwd.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PwdDetailScreen(
    viewModel: PwdDetailViewModel = hiltViewModel(),
    id: Int,
    onBackClick: () -> Unit
) {
    val pwd by viewModel.pwd.collectAsState()
    val isEditMode by viewModel.isEditMode


    val form = viewModel.formState
    val lifecycleOwner = LocalLifecycleOwner.current

    var showDialog by remember { mutableStateOf(false) }
    var showNoNetworkDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadPwd(id)
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
                viewModel.toggleEditMode() // navigate back
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detail") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    // Save button (only when editing)
                    if (isEditMode) {
                        IconButton(onClick = { viewModel.onSaveClick() }) {
                            Icon(Icons.Default.Done, contentDescription = "Save")
                        }
                    }

                    // Edit / View toggle button
                    IconButton(onClick = { viewModel.toggleEditMode() }) {
                        Icon(
                            imageVector = if (isEditMode) Icons.Default.Close else Icons.Default.Edit,
                            contentDescription = if (isEditMode) "Cancel Edit" else "Edit"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
  /*      Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {*/
            if (isEditMode) {
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
            } else {
                Text("Username/Email\": ${form.key}", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Password:\n${form.value}")
            }
        }
    }


    /*   LaunchedEffect(Unit) {
           viewModel.loadPwd(id)
       }

       pwd?.let {
           var key by remember { mutableStateOf(it.key) }
           var value by remember { mutableStateOf(it.value) }

           Column(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(16.dp)
           ) {
               if (isEditMode) {
                   OutlinedTextField(
                       value = key,
                       onValueChange = { key = it },
                       label = { Text("Username/Email") },
                       modifier = Modifier.fillMaxWidth()
                   )
                   Spacer(modifier = Modifier.height(12.dp))
                   OutlinedTextField(
                       value = value,
                       onValueChange = { value = it },
                       label = { Text("Password") },
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
               } else {
                   Text("Username/Email\": ${it.key}", style = MaterialTheme.typography.titleLarge)
                   Spacer(modifier = Modifier.height(8.dp))
                   Text("Password:\n${it.value}")
               }

               Spacer(modifier = Modifier.height(16.dp))

               Row {
                   Button(onClick = {
                       if (isEditMode) {
                           viewModel.updatePwd(key, value)
                       } else {
                           viewModel.toggleEditMode()
                       }
                   }) {
                       Text(if (isEditMode) "Save" else "Edit")
                   }

                   Spacer(modifier = Modifier.width(8.dp))

                   if (isEditMode) {
                       OutlinedButton(onClick = {
                           viewModel.toggleEditMode()
                       }) {
                           Text("Cancel")
                       }
                   }
               }
           }
       } ?: run {
          // CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterVertically))
       }*/
}


