package com.jpn.domain.model

import android.net.Uri

enum class MediaType { Photo, Video }

data class MediaItem(
    val uri: Uri,
    val id: Long,
    val type: MediaType
)