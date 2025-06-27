package com.jpn.domain.profile.repository

import com.jpn.domain.profile.model.Profile

interface ProfileRepository {
    suspend fun getProfile(): Profile
    //suspend fun updateProfile(profile: Profile)
}