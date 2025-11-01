package com.jpn.domain.repository

import com.jpn.domain.model.MediaItem

interface VaultRepository {

    suspend fun saveToVault(items: List<MediaItem>)
    suspend fun deleteVaultPhoto(photo: MediaItem)
    suspend fun getVaultPhotos(): List<MediaItem>
    suspend fun getVaultVideos(): List<MediaItem>
}