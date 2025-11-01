package com.jpn.domain.usecase

import com.jpn.domain.model.Profile
import com.jpn.domain.repository.ProfileRepository

class GetProfileUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke(): Profile = repository.getProfile()
}