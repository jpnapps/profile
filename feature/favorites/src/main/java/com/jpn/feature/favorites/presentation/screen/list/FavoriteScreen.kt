// FavoriteScreen.kt
package com.jpn.feature.favorites.presentation.screen.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jpn.feature.favorites.domain.model.Favorite
import com.jpn.feature.favorites.presentation.viewmodel.FavoriteViewModel

/*@Composable
fun FavoriteScreen2(viewModel: FavoriteViewModel) {
    val favorites by viewModel.favorites.collectAsState()

    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Favorite")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(favorites) { item ->
                FavoriteItemRow(item)
            }
        }
    }

    if (showAddDialog) {
        AddFavoriteDialog(
            onAdd = {
                viewModel.addFavorite(it)
                showAddDialog = false
            },
            onDismiss = { showAddDialog = false }
        )
    }
}*/

@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel) {
    val favorites by viewModel.favorites.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Favorite")
            }
        }
    ) {
        LazyColumn(contentPadding = it) {
            items(favorites) { favorite ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        AsyncImage(
                            model = favorite.imageUrl,
                            contentDescription = favorite.name,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(text = favorite.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = favorite.description, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }

        if (showDialog) {
            AddFavoriteDialog(
                onAdd = { name, img, desc ->
                    viewModel.addFavorite(
                        Favorite(name = name, imageUrl = img, description = desc)
                    )
                    showDialog = false
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}
@Composable
fun AddFavoriteDialog(
    onAdd: (String, String, String) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var img by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Favorite") },
        text = {
            Column {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                OutlinedTextField(value = img, onValueChange = { img = it }, label = { Text("Image URL") })
                OutlinedTextField(value = desc, onValueChange = { desc = it }, label = { Text("Description") })
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onAdd(name, img, desc)
            }) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

/*@Composable
fun FavoriteItemRow(item: FavoriteItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(item.imageUrl),
            contentDescription = item.name,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(item.name, style = MaterialTheme.typography.titleMedium)
            Text(item.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun AddFavoriteDialog(onAdd: (FavoriteItem) -> Unit, onDismiss: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Favorite") },
        text = {
            Column {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                OutlinedTextField(value = imageUrl, onValueChange = { imageUrl = it }, label = { Text("Image URL") })
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if(name.isNotBlank() && imageUrl.isNotBlank() && description.isNotBlank()) {
                        onAdd(
                            Favorite(
                            name = name,
                            imageUrl = imageUrl,
                            description = description
                        )
                        )
                    }
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}*/
