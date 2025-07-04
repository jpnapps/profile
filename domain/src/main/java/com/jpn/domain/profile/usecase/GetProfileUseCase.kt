package com.jpn.domain.profile.usecase

import com.jpn.domain.profile.model.Profile
import com.jpn.domain.profile.repository.ProfileRepository

class GetProfileUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke(): Profile = repository.getProfile()
}