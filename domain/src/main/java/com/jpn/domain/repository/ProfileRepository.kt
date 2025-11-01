package com.jpn.domain.repository

import com.jpn.domain.model.Profile

interface ProfileRepository {
    suspend fun getProfile(): Profile
    //suspend fun updateProfile(profile: Profile)
}