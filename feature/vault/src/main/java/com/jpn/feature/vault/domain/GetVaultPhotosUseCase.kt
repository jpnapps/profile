package com.jpn.feature.vault.domain

import com.jpn.domain.model.MediaItem
import com.jpn.domain.repository.VaultRepository
import com.jpn.domain.usecase.vault.GetVaultPhotosUseCase
import javax.inject.Inject

class GetVaultPhotosUseCaseImpl @Inject constructor(
    private val vaultRepository: VaultRepository
) : GetVaultPhotosUseCase {
    override suspend operator fun invoke(): List<MediaItem> =
        vaultRepository.getVaultPhotos()
}
