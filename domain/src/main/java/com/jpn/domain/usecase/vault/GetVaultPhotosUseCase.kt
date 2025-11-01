package com.jpn.domain.usecase.vault

import com.jpn.domain.model.MediaItem

interface GetVaultPhotosUseCase {
    suspend operator fun invoke(): List<MediaItem>
}

interface GetMediaPhotosUseCase {
    suspend operator fun invoke(): List<MediaItem>
}