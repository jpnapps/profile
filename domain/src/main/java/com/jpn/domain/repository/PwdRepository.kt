package com.jpn.domain.repository

import com.jpn.domain.model.PwdData
import kotlinx.coroutines.flow.Flow

interface PwdRepository {
    fun getPwds(): Flow<List<PwdData>>
    suspend fun addPwd(item: PwdData)
    fun getPwdById(id:Int): Flow<PwdData>
    suspend fun updatePwd(item: PwdData)
    //fun exportPwds(): Flow<List<String>>
    suspend fun exportPwds(): String
}