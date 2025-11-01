package com.jpn.feature.vault.data.repository

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.jpn.core.utils.managers.LogManager
import com.jpn.domain.model.MediaItem
import com.jpn.domain.model.MediaType
import com.jpn.domain.repository.MediaRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import javax.inject.Inject

class MediaRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val logManager: LogManager
) : MediaRepository {

    override suspend fun deleteFromGallery(uri: Uri) {
        context.contentResolver.delete(uri, null, null)
    }

    override suspend fun restoreToGallery(file: File): Uri {
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, file.name)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        uri?.let {
            FileInputStream(file).use { input ->
                resolver.openOutputStream(it)?.use { output -> input.copyTo(output) }
            }
        }
        return uri!!
    }


    override suspend fun getPhoneMedia(type: MediaType): List<MediaItem> =
        withContext(Dispatchers.IO) {
            val mediaItems = mutableListOf<MediaItem>()
            val collection = when (type) {
                MediaType.Photo -> MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                MediaType.Video -> MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            }
            logManager.d("repo getPhoneMedia " + collection)
            /*  val projection = arrayOf(
                  MediaStore.MediaColumns._ID,
                  MediaStore.MediaColumns.MIME_TYPE
              )*/
            val projection = arrayOf(
                MediaStore.MediaColumns._ID,
                MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.MediaColumns.DATE_ADDED
            )
            try {
                context.contentResolver.query(
                    collection,
                    projection,
                    null,
                    null,
                    "${MediaStore.MediaColumns.DATE_ADDED} DESC"
                )?.use { cursor ->
                    val idIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID)
                    val nameIndex =
                        cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)
                    val dateIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_ADDED)

                    while (cursor.moveToNext()) {
                        val id = cursor.getLong(idIndex)
                        val name = cursor.getString(nameIndex)
                        val dateAdded = cursor.getLong(dateIndex)

                        val contentUri = ContentUris.withAppendedId(collection, id)

                        mediaItems.add(MediaItem(uri = contentUri, id = id, type = type))
                    }
                }
            } catch (e: SecurityException) {
                Log.e("VaultRepository", "Permission denied: ${e.message}")
                logManager.d("repo Permission denied: ${e.message}")
            } catch (e: Exception) {
                Log.e("VaultRepository", "Error fetching media: ${e.message}")
                logManager.d("repo Error fetching media: ${e.message}")
            }
            logManager.d("repo getPhoneMedia mediaItems " + mediaItems.size)
            mediaItems
        }
}
