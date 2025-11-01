package com.jpn.feature.vault.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.google.gson.Gson
import com.jpn.core.utils.managers.LogManager
import com.jpn.domain.model.MediaItem
import com.jpn.domain.model.MediaType
import com.jpn.domain.repository.VaultRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class VaultRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val logManager: LogManager,
    private val gson: Gson
) : VaultRepository {

    private val rootDir: File by lazy { createDir(context.filesDir, "jithish") }
    private val vaultDir: File by lazy { createDir(rootDir, "vault") }
    private val vaultPhotosDir: File by lazy { createDir(vaultDir, "photos") }
    private val vaultVideosDir: File by lazy { createDir(vaultDir, "videos") }

    private fun createDir(parent: File, name: String): File {
        return File(parent, name).apply {
            if (!exists()) {
                val created = mkdirs()
                if (!created) logManager.d("Failed to create directory: ${absolutePath}")
            }
        }
    }

    /*    private val vaultDir: File
            get() = File(context.filesDir, "vault").apply { if (!exists()) mkdirs() }


        private val vaultPhotosDir = File(context.filesDir, "vault_photos").apply { mkdirs() }*/


    override suspend fun getVaultPhotos(): List<MediaItem> =
        vaultPhotosDir.listFiles()?.map { file ->
            MediaItem(
                uri = file.toUri(),              // convert File → Uri
                id = file.lastModified(),        // or generate a unique id
                type = MediaType.Photo           // since vault photos
            )
        } ?: emptyList()

    override suspend fun getVaultVideos(): List<MediaItem> {
        TODO("Not yet implemented")
    }


    override suspend fun saveToVault(items: List<MediaItem>) = withContext(Dispatchers.IO) {
        items.forEach { item ->
            try {
                val inputStream = context.contentResolver.openInputStream(item.uri)
                val fileName = "vault_${item.id}"
                val destFile = File(vaultPhotosDir, fileName)

                inputStream?.use { input ->
                    destFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }

            } catch (e: Exception) {
                Log.e("VaultRepository", "Failed to save ${item.uri}: ${e.message}")
            }
        }
    }

    fun hidePhoto(uri: Uri) {
        /*val name = getFileName(uri)
        val input = context.contentResolver.openInputStream(uri) ?: return
        val hiddenFile = File(vaultDir, name)
        input.use { it.copyTo(hiddenFile.outputStream()) }

        // Optionally move original to hidden folder
        val originalPath = getPathFromUri(uri)
        originalPath?.let {
            val originalFile = File(it)
            val hiddenOriginal = File(originalFile.parentFile, ".${originalFile.name}")
            originalFile.renameTo(hiddenOriginal)
        }*/
    }

    override suspend fun deleteVaultPhoto(photo: MediaItem) {

    }
}