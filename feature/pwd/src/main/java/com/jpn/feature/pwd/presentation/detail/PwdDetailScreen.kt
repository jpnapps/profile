package com.jpn.feature.pwd.presentation.detail

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jpn.core.ui.cards.WhiteCard
import com.jpn.core.ui.containers.ScreenContainer
import com.jpn.core.ui.dialogs.ErrorDialog
import com.jpn.core.ui.dialogs.LoadingDialog
import com.jpn.core.ui.dialogs.NoNetworkDialog
import com.jpn.core.ui.topbars.DetailScreenTopBar
import com.jpn.feature.pwd.R
import com.jpn.feature.pwd.presentation.add.AddPwdUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PwdDetailScreen(
    viewModel: PwdDetailViewModel = hiltViewModel(),
    id: Int,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit,
) {
    val form = viewModel.formState
    Log.d("jpLogs", "PwdDetailScreen id: " + id)
    var showNoNetworkDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(id) {
        Log.d("jpLogs", "PwdDetailScreen  LaunchedEffect  id: " + id)
        if (id > 0)
            viewModel.loadPwd(id)
        Log.d("jpLogs", "PwdDetailScreen  LaunchedEffect  after id: " + id)
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
            DetailScreenTopBar(
                form.title,
                stringResource(R.string.edit),
                onBackClick = onBackClick,
                onEditClick = onEditClick
            )
        }
    ) { innerPadding ->
        ScreenContainer(innerPadding) {
            WhiteCard {
                if (!form.isFieldDeleted) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(form.fieldLabel, style = MaterialTheme.typography.labelMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(form.fieldValue, style = MaterialTheme.typography.titleLarge)
                }
                if (!form.isSecureDeleted) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(form.secureLabel, style = MaterialTheme.typography.labelMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(form.secureValue, style = MaterialTheme.typography.titleLarge)
                }
            }
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



