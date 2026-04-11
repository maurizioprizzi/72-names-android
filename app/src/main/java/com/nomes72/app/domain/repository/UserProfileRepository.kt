package com.nomes72.app.domain.repository

import com.nomes72.app.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {
    fun getUserProfile(): Flow<UserProfile?>
    suspend fun saveUserProfile(profile: UserProfile)
    suspend fun clearUserProfile()
}