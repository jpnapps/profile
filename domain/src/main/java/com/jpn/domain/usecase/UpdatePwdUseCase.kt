package com.jpn.domain.usecase

import com.jpn.domain.model.PwdData
import com.jpn.domain.repository.PwdRepository

class UpdatePwdUseCase(private val repo: PwdRepository) {
    suspend operator fun invoke(pwd: PwdData) = repo.updatePwd(pwd)
}
