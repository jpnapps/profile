package com.jpn.feature.pwd.data.repository

import com.jpn.core.local.dao.PwdDao
import com.jpn.core.utils.managers.LogManager
import com.jpn.domain.profile.model.PwdData
import com.jpn.domain.profile.repository.PwdRepository
import com.jpn.feature.pwd.data.mapper.toDomain
import com.jpn.feature.pwd.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PwdRepositoryImpl @Inject constructor(
    private val dao: PwdDao,
    private val logManager: LogManager
) : PwdRepository {

    override fun getPwds(): Flow<List<PwdData>> {
        return dao.getAllPwds().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun addPwd(pwd: PwdData) {
        logManager.d("PDRepo addPwd ",pwd)
        val entitiy= pwd.toEntity()
        dao.insertPwd(entitiy)
        logManager.d("PDRepo add entitiy ",entitiy)
    }

    override fun getPwdById(id: Int): Flow<PwdData> {
       return dao.getPwdById(id).map { it.toDomain() }
    }

    override suspend fun updatePwd(pwd: PwdData) {
        //dao.updatePwd(pwd.toEntity())
        logManager.d("PDRepo updatePWD ",pwd)
        val entitiy= pwd.toEntity()
        dao.updatePwd(entitiy)
        logManager.d("PDRepo update entitiy ",entitiy)
    }
}