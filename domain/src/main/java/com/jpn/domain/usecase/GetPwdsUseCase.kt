package com.jpn.domain.usecase

import com.jpn.domain.model.PwdData
import com.jpn.domain.repository.PwdRepository
import kotlinx.coroutines.flow.Flow

class GetPwdsUseCase(private val repo: PwdRepository) {
    operator fun invoke(): Flow<List<PwdData>> = repo.getPwds()
}
