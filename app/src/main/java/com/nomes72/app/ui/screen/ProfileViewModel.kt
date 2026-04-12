package com.nomes72.app.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomes72.app.domain.model.SacredName
import com.nomes72.app.domain.model.UserProfile
import com.nomes72.app.domain.repository.UserProfileRepository
import com.nomes72.app.domain.usecase.GetNameByNumberUseCase
import com.nomes72.app.domain.util.AngelCalendar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

data class ProfileUiState(
    val profile: UserProfile? = null,
    val personalName: SacredName? = null,
    val isLoading: Boolean = true,
    val isSaved: Boolean = false
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userProfileRepository: UserProfileRepository,
    private val getNameByNumberUseCase: GetNameByNumberUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            userProfileRepository.getUserProfile().collect { profile ->
                if (profile != null) {
                    val number = AngelCalendar.getAngelNumberByDate(profile.birthDate)
                    val personalName = getNameByNumberUseCase(number)
                    _uiState.value = ProfileUiState(
                        profile = profile,
                        personalName = personalName,
                        isLoading = false,
                        isSaved = true
                    )
                } else {
                    _uiState.value = ProfileUiState(
                        isLoading = false,
                        isSaved = false
                    )
                }
            }
        }
    }

    fun saveProfile(birthDate: LocalDate) {
        viewModelScope.launch {
            val profile = UserProfile(
                birthDate = birthDate,
                preferredLanguage = "pt"
            )
            userProfileRepository.saveUserProfile(profile)
        }
    }

    fun clearProfile() {
        viewModelScope.launch {
            userProfileRepository.clearUserProfile()
        }
    }
}