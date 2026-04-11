package com.nomes72.app.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.nomes72.app.domain.model.UserProfile
import com.nomes72.app.domain.repository.UserProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProfileRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserProfileRepository {

    private companion object {
        val KEY_BIRTH_DATE = stringPreferencesKey("birth_date")
        val KEY_BIRTH_TIME = stringPreferencesKey("birth_time")
        val KEY_BIRTH_PLACE = stringPreferencesKey("birth_place")
        val KEY_PREFERRED_LANGUAGE = stringPreferencesKey("preferred_language")
    }

    override fun getUserProfile(): Flow<UserProfile?> {
        return dataStore.data.map { prefs ->
            val dateString = prefs[KEY_BIRTH_DATE] ?: return@map null

            UserProfile(
                birthDate = LocalDate.parse(dateString),
                birthTime = prefs[KEY_BIRTH_TIME]?.let { LocalTime.parse(it) },
                birthPlace = prefs[KEY_BIRTH_PLACE],
                preferredLanguage = prefs[KEY_PREFERRED_LANGUAGE] ?: "pt"
            )
        }
    }

    override suspend fun saveUserProfile(profile: UserProfile) {
        dataStore.edit { prefs ->
            prefs[KEY_BIRTH_DATE] = profile.birthDate.toString()
            profile.birthTime?.let { prefs[KEY_BIRTH_TIME] = it.toString() }
                ?: prefs.remove(KEY_BIRTH_TIME)
            profile.birthPlace?.let { prefs[KEY_BIRTH_PLACE] = it }
                ?: prefs.remove(KEY_BIRTH_PLACE)
            prefs[KEY_PREFERRED_LANGUAGE] = profile.preferredLanguage
        }
    }

    override suspend fun clearUserProfile() {
        dataStore.edit { it.clear() }
    }
}