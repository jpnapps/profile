package com.jpn.domain.profile.usecase

import com.jpn.domain.profile.model.PwdData
import com.jpn.domain.profile.repository.PwdRepository

class AddPwdUseCase(private val repo: PwdRepository) {
    suspend operator fun invoke(pwd: PwdData) = repo.addPwd(pwd)
}
