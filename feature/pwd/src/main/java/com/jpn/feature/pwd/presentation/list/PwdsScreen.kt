package com.jpn.feature.pwd.presentation.list

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jpn.core.ui.box.BoxGreyBorder
import com.jpn.domain.profile.model.PwdData

@Composable
fun PwdsScreen(
    viewModel: PwdsViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit,
    onAddClick: () -> Unit
) {
    val pwdUiModels by viewModel.pwdUiModels.collectAsState()
    var showSecureEntry by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<PwdData?>(null) }
    var selectedItemId by remember { mutableStateOf<Int>(-1) }

    Scaffold(
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
                    /*  Box(
                          modifier = Modifier
                              .fillMaxWidth()
                              .padding(horizontal = 12.dp, vertical = 6.dp)
                              .border(
                                  width = 1.dp,
                                  color = Color.LightGray,
                                  shape = RoundedCornerShape(8.dp)
                              )
                              .padding(12.dp)
                              .clickable {
                                  showSecureEntry = true
                                  selectedItemId = item.id
                              }
                      ) */
                    Column {
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.labelLarge
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = item.xxText,
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