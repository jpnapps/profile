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
import androidx.compose.ui.platform.LocalContext
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
import com.jpn.feature.notes.R

@Composable
fun AddNoteScreen(
    viewModel: AddNoteViewModel = hiltViewModel(),
    onNoteSaved: () -> Unit
) {
    /*  val title by viewModel.title.collectAsState()
      val content by viewModel.content.collectAsState()*/

    val form = viewModel.formState
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var showDialog by remember { mutableStateOf(false) }
    var showNoNetworkDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.eventFlow.collect { event ->
                when (event) {
                    is AddNoteEvent.SaveSuccess -> {
                        showDialog = true
                    }

                    is AddNoteEvent.Error -> {
                        errorMessage = event.message
                        //Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                    }

                    AddNoteEvent.NoNetwork -> {
                        showNoNetworkDialog = true
                        //Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    if (showDialog) {
        SuccessDialog(
            message = "Note added successfully!",
            onDismiss = {
                showDialog = false
                onNoteSaved() // navigate back
                //navController.popBackStack() // this takes you back to note list screen
                //onSuccess()
            }
        )
    }

    if (showNoNetworkDialog) {
        NoNetworkDialog(onRetry = {}) { }
    }

    errorMessage?.let {
        ErrorDialog(it) {}
    }

    if (uiState == AddNoteUiState.Loading)
        LoadingDialog()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.add_note_title),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = form.title,
            onValueChange = { viewModel.onTitleChange(it) },
            //onValueChange = viewModel::onTitleChange,
            label = { Text(stringResource(R.string.note_title_label)) },
            isError = form.titleError != null,
            supportingText = {
                form.titleError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = form.content,
            onValueChange = viewModel::onContentChange,
            label = { Text(stringResource(R.string.note_content_label)) },
            isError = form.contentError != null,
            supportingText = {
                form.contentError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = form.imageLink,
            onValueChange = viewModel::onImageLinkChange,
            label = { Text("Image Link ") }
        )
        Spacer(modifier = Modifier.height(12.dp))
        ReferralLinksInput(
            refLinks = viewModel.formState.refLinks,
            onAddLink = viewModel::addRefLink,
            onRemoveLink = viewModel::removeRefLink
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

