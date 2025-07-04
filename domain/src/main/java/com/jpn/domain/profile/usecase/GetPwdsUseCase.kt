package com.jpn.domain.profile.usecase

import com.jpn.domain.profile.model.PwdData
import com.jpn.domain.profile.repository.PwdRepository
import kotlinx.coroutines.flow.Flow

class GetPwdsUseCase(private val repo: PwdRepository) {
    operator fun invoke(): Flow<List<PwdData>> = repo.getPwds()
}
