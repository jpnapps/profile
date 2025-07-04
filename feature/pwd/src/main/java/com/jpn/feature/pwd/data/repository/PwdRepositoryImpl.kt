package com.jpn.feature.favorites.data.repository

import com.jpn.core.local.dao.PwdDao
import com.jpn.domain.profile.model.PwdData
import com.jpn.domain.profile.repository.PwdRepository
import com.jpn.feature.favorites.data.mapper.toDomain
import com.jpn.feature.favorites.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PwdRepositoryImpl @Inject constructor(
    private val dao: PwdDao
) : PwdRepository {

    override fun getPwds(): Flow<List<PwdData>> {
        return dao.getAllPwds().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun addPwd(pwd: PwdData) {
        dao.insertPwd(pwd.toEntity())
    }

    override fun getPwdById(id: Int): Flow<PwdData> {
       return dao.getPwdById(id).map { it.toDomain() }
    }

    override suspend fun updatePwd(pwd: PwdData) {
        dao.updatePwd(pwd.toEntity())
    }
}