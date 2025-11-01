package com.jpn.domain.usecase

import com.jpn.domain.model.PwdData
import com.jpn.domain.repository.PwdRepository
import kotlinx.coroutines.flow.Flow

// UseCase
class GetPwdByIdUseCase(
    private val repository: PwdRepository
) {
     operator fun invoke(id: Int): Flow<PwdData> = repository.getPwdById(id)
}
