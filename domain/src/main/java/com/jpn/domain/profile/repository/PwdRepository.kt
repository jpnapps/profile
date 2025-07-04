package com.jpn.domain.profile.repository

import com.jpn.domain.profile.model.PwdData
import kotlinx.coroutines.flow.Flow

interface PwdRepository {
    fun getPwds(): Flow<List<PwdData>>
    suspend fun addPwd(item: PwdData)
    fun getPwdById(id:Int): Flow<PwdData>
    suspend fun updatePwd(item: PwdData)
}