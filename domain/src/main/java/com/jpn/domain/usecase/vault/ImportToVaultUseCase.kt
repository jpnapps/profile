package com.jpn.domain.usecase.vault

import android.content.IntentSender
import com.jpn.domain.model.MediaItem

interface ImportToVaultUseCase {
    //suspend operator fun invoke(uris: List<Uri>)
    suspend operator fun invoke(items: List<MediaItem>,  onRequestDeletePermission: suspend (IntentSender) -> Unit)

}