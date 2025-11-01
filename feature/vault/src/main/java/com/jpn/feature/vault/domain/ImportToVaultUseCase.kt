package com.jpn.feature.vault.domain

import android.content.IntentSender
import com.jpn.core.utils.managers.LogManager
import com.jpn.domain.model.MediaItem
import com.jpn.domain.repository.MediaRepository
import com.jpn.domain.repository.VaultRepository
import com.jpn.domain.usecase.vault.ImportToVaultUseCase
import javax.inject.Inject

class ImportToVaultUseCaseImpl @Inject constructor(
    private val vaultRepository: VaultRepository,
    private val mediaRepository: MediaRepository,
    private val logManager: LogManager
) : ImportToVaultUseCase {
   /* override suspend operator fun invoke(uris: List<Uri>) {
         uris.forEach { uri ->
             vaultRepository.saveToVault(uri)
             mediaRepository.deleteFromGallery(uri)
         }
    }*/

    override suspend fun invoke(items: List<MediaItem> ,  onRequestDeletePermission: suspend (IntentSender) -> Unit) {
        vaultRepository.saveToVault(items)
        logManager.d("IVUse invoke items  "+items.size)
        items.forEach { item ->
         /*   try{
            mediaRepository.deleteFromGallery(item.uri)}
            catch (e:Exception){
                logManager.d("IVUse invoke  exce  "+e.message)
            }*/

            try {
                mediaRepository.deleteFromGallery(item.uri)
            } catch (e: Exception) {
                logManager.d("IVUse invoke exce ${e.message}")
                //  If it’s a RecoverableSecurityException, propagate it
               /* if (e is android.app.RecoverableSecurityException) {
                    onRequestDeletePermission(e.userAction.actionIntent.intentSender)
                }*/
            }
        }
    }
}
