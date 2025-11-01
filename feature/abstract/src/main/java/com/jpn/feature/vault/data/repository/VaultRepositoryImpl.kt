package com.jpn.feature.vault.data.repository

import com.google.gson.Gson
import com.jpn.core.utils.managers.LogManager
import com.jpn.domain.repository.VaultRepository
import javax.inject.Inject

class VaultRepositoryImpl @Inject constructor(
    private val logManager: LogManager,
    private val gson: Gson
) : VaultRepository {

}