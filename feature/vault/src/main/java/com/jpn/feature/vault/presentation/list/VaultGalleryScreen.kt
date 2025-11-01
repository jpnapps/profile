package com.jpn.feature.vault.presentation.list

import android.Manifest
import android.app.Activity.RESULT_OK
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import com.jpn.core.ui.dialogs.SuccessDialog
import com.jpn.core.ui.events.UiEvent
import com.jpn.core.ui.topbars.AppTopBar
import com.jpn.domain.model.MediaItem
import com.jpn.domain.model.MediaType

@Composable
fun VaultGalleryScreen(
    viewModel: VaultGalleryViewModel = hiltViewModel(),
    mediaType: MediaType = MediaType.Photo, // Photo or Video
    onBackClick: () -> Unit,
    onDone: () -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var showSucessDialog by remember { mutableStateOf(false) }

    val permissions = if (Build.VERSION.SDK_INT >= 33) {
        arrayOf(
            Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.READ_MEDIA_VIDEO
        )
    } else {
        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { granted ->
        if (granted.values.all { it }) {
            viewModel.loadMedia() // only after permissions granted
        }
    }

    val deleteLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // User granted delete permission
            // viewModel.refreshMedia() // reload gallery to reflect deletion
        }
    }


    LaunchedEffect(Unit) {
        launcher.launch(permissions)
    }
    LaunchedEffect(Unit) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is UiEvent.Success -> {
                        showSucessDialog = true
                    }

                    is UiEvent.RequestDeletePermission -> {
                        /*deleteLauncher.launch(
                            IntentSenderRequest.Builder(event.intentSender).build()
                        )*/
                    }

                    else -> {

                    }
                }
            }
        }
    }
    val mediaItems by viewModel.mediaItems.collectAsState()
    val selectedItems by viewModel.selectedItems.collectAsState()

    if (showSucessDialog) {
        SuccessDialog("Added Successfully ") {
            // navigate back
            onBackClick()
        }
    }
    Scaffold(
        topBar = {
            AppTopBar(
                title = if (mediaType == MediaType.Photo) "Select Photos " else "Select Videos",
                onBackClick = onBackClick,
                actions = {
                    if (selectedItems.isNotEmpty()) {
                        TextButton(onClick = {
                            viewModel.addToVault()
                        }

                        ) {
                            Text("Done (${selectedItems.size})")
                        }
                    }
                })
        }) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(mediaItems.size) { pos ->
                val item = mediaItems[pos]
                GalleryItem(
                    item = item,
                    isSelected = selectedItems.contains(item),
                    onClick = { viewModel.toggleSelection(item) })
            }
        }
    }
}


@Composable
fun GalleryItem(
    item: MediaItem, isSelected: Boolean, onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }) {
        AsyncImage(
            model = item.uri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
            )
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color.Green,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
            )
        }
    }
}
