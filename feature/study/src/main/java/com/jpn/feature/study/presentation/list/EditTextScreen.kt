package com.jpn.feature.study.presentation.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jpn.core.ui.topbars.AddScreenTopBar
import com.jpn.feature.study.presentation.viewmodel.StudyAddScreenViewModel

@Composable
fun EditTextScreen(
    viewModel: StudyAddScreenViewModel= hiltViewModel(),
    title: String = "Edit Note",
    initialText: String = "",
    onSave: () -> Unit,
    onBack: () -> Unit
) {
    var text by rememberSaveable { mutableStateOf(initialText) }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            AddScreenTopBar(
                title = "Add Note",
                onBackClick = onBack,
                onSaveClick = {
                    if(viewModel.saveAdd(text))
                        onSave()
                }
            )
        }
    ) { innerPadding ->
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .focusRequester(focusRequester),
            textStyle = MaterialTheme.typography.bodyLarge.copy(lineHeight = 22.sp),
            placeholder = { Text("Type here...") },
            singleLine = false,
            maxLines = Int.MAX_VALUE
        )
    }

    // Focus text field automatically
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

