package com.jpn.feature.vault.domain

import com.jpn.domain.model.MediaItem
import com.jpn.domain.model.MediaType
import com.jpn.domain.repository.MediaRepository
import com.jpn.domain.usecase.vault.GetMediaPhotosUseCase
import javax.inject.Inject

class GetMediaPhotosUseCaseImpl @Inject constructor(
    private val mediaRepository: MediaRepository
) : GetMediaPhotosUseCase {
    override suspend operator fun invoke(): List<MediaItem> =
        mediaRepository.getPhoneMedia(MediaType.Photo)
}
