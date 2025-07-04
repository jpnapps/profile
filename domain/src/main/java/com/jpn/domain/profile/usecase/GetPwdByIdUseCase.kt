package com.jpn.domain.profile.usecase

import com.jpn.domain.profile.model.PwdData
import com.jpn.domain.profile.repository.PwdRepository
import kotlinx.coroutines.flow.Flow

// UseCase
class GetPwdByIdUseCase(
    private val repository: PwdRepository
) {
     operator fun invoke(id: Int): Flow<PwdData> = repository.getPwdById(id)
}
