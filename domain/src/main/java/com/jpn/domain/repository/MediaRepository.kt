package com.jpn.domain.repository

import android.net.Uri
import com.jpn.domain.model.MediaItem
import com.jpn.domain.model.MediaType
import java.io.File

interface MediaRepository {
    suspend fun deleteFromGallery(uri: Uri)
    suspend fun restoreToGallery(file: File): Uri
    suspend fun getPhoneMedia(type: MediaType): List<MediaItem>
}
