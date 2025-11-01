package com.jpn.domain.usecase

import com.jpn.domain.repository.PwdRepository

class ExportPwdsUseCase(private val repo: PwdRepository) {
    operator suspend fun invoke(): String = repo.exportPwds()
}
