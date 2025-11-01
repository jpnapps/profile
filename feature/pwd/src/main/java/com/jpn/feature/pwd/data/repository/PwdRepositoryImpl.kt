package com.jpn.feature.pwd.data.repository

import com.google.gson.Gson
import com.jpn.core.local.dao.PwdDao
import com.jpn.core.utils.managers.LogManager
import com.jpn.domain.model.PwdData
import com.jpn.domain.repository.PwdRepository
import com.jpn.feature.pwd.data.mapper.toDomain
import com.jpn.feature.pwd.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PwdRepositoryImpl @Inject constructor(
    private val dao: PwdDao,
    private val logManager: LogManager,
    private val gson : Gson
) : PwdRepository {

    override fun getPwds(): Flow<List<PwdData>> {
        return dao.getAllPwds().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun addPwd(pwd: PwdData) {
        logManager.d("PDRepo addPwd ", pwd)
        val entitiy = pwd.toEntity()
        dao.insertPwd(entitiy)
        logManager.d("PDRepo add entitiy ", entitiy)
    }

    override fun getPwdById(id: Int): Flow<PwdData> {
        return dao.getPwdById(id).map { it.toDomain() }
    }

    override suspend fun updatePwd(pwd: PwdData) {
        logManager.d("PDRepo updatePWD ", pwd)
        val entitiy = pwd.toEntity()
        dao.updatePwd(entitiy)
        logManager.d("PDRepo update entitiy ", entitiy)
    }

    override suspend fun exportPwds(): String {
        val allItems = dao.getPwds()
        return gson.toJson(allItems)
    }
}