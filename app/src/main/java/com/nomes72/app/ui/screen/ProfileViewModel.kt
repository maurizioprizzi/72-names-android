package com.nomes72.app.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomes72.app.domain.model.SacredName
import com.nomes72.app.domain.model.UserProfile
import com.nomes72.app.domain.repository.UserProfileRepository
import com.nomes72.app.domain.usecase.GetNameByNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.MonthDay
import javax.inject.Inject

data class ProfileUiState(
    val profile: UserProfile? = null,
    val personalName: SacredName? = null,
    val isLoading: Boolean = true,
    val isSaved: Boolean = false
)

/**
 * Representa um período de regência de um anjo no calendário cabalístico.
 * Cada anjo rege aproximadamente 5 dias, começando em 21 de março.
 */
private data class AngelPeriod(
    val number: Int,
    val startMonth: Int,
    val startDay: Int,
    val endMonth: Int,
    val endDay: Int
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
                    val personalName = calculatePersonalName(profile.birthDate)
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

    /**
     * Calcula o Nome pessoal baseado no calendário cabalístico dos 72 anjos.
     *
     * Cada anjo rege um período de aproximadamente 5 dias do ano,
     * começando em 21 de março (equinócio de primavera).
     * Este é o método tradicional usado na Cabala.
     */
    private suspend fun calculatePersonalName(birthDate: LocalDate): SacredName? {
        val nameNumber = getAngelNumberByDate(birthDate.monthValue, birthDate.dayOfMonth)
        return getNameByNumberUseCase(nameNumber)
    }

    private fun getAngelNumberByDate(month: Int, day: Int): Int {
        val target = MonthDay.of(month, day)

        for (period in angelPeriods) {
            val start = MonthDay.of(period.startMonth, period.startDay)
            val end = MonthDay.of(period.endMonth, period.endDay)

            if (period.startMonth <= period.endMonth) {
                // Período normal (não cruza virada de ano)
                if (target >= start && target <= end) return period.number
            } else {
                // Período que cruza virada de ano (ex: dez → jan)
                if (target >= start || target <= end) return period.number
            }
        }

        // Fallback para dias que caem entre períodos (ex: 29 de fev)
        return 1
    }

    companion object {
        /**
         * Tabela dos 72 períodos angélicos baseada no calendário cabalístico tradicional.
         * Início: 21 de março (equinócio de primavera).
         * Cada anjo rege aproximadamente 5 dias.
         */
        private val angelPeriods = listOf(
            AngelPeriod(1, 3, 21, 3, 25),
            AngelPeriod(2, 3, 26, 3, 30),
            AngelPeriod(3, 3, 31, 4, 4),
            AngelPeriod(4, 4, 5, 4, 9),
            AngelPeriod(5, 4, 10, 4, 14),
            AngelPeriod(6, 4, 15, 4, 20),
            AngelPeriod(7, 4, 21, 4, 25),
            AngelPeriod(8, 4, 26, 4, 30),
            AngelPeriod(9, 5, 1, 5, 5),
            AngelPeriod(10, 5, 6, 5, 10),
            AngelPeriod(11, 5, 11, 5, 15),
            AngelPeriod(12, 5, 16, 5, 20),
            AngelPeriod(13, 5, 21, 5, 25),
            AngelPeriod(14, 5, 26, 5, 31),
            AngelPeriod(15, 6, 1, 6, 5),
            AngelPeriod(16, 6, 6, 6, 10),
            AngelPeriod(17, 6, 11, 6, 15),
            AngelPeriod(18, 6, 16, 6, 21),
            AngelPeriod(19, 6, 22, 6, 26),
            AngelPeriod(20, 6, 27, 7, 1),
            AngelPeriod(21, 7, 2, 7, 6),
            AngelPeriod(22, 7, 7, 7, 11),
            AngelPeriod(23, 7, 12, 7, 16),
            AngelPeriod(24, 7, 17, 7, 22),
            AngelPeriod(25, 7, 23, 7, 27),
            AngelPeriod(26, 7, 28, 8, 1),
            AngelPeriod(27, 8, 2, 8, 6),
            AngelPeriod(28, 8, 7, 8, 12),
            AngelPeriod(29, 8, 13, 8, 17),
            AngelPeriod(30, 8, 18, 8, 22),
            AngelPeriod(31, 8, 23, 8, 28),
            AngelPeriod(32, 8, 29, 9, 2),
            AngelPeriod(33, 9, 3, 9, 7),
            AngelPeriod(34, 9, 8, 9, 12),
            AngelPeriod(35, 9, 13, 9, 17),
            AngelPeriod(36, 9, 18, 9, 23),
            AngelPeriod(37, 9, 24, 9, 28),
            AngelPeriod(38, 9, 29, 10, 3),
            AngelPeriod(39, 10, 4, 10, 8),
            AngelPeriod(40, 10, 9, 10, 13),
            AngelPeriod(41, 10, 14, 10, 18),
            AngelPeriod(42, 10, 19, 10, 23),
            AngelPeriod(43, 10, 24, 10, 28),
            AngelPeriod(44, 10, 29, 11, 2),
            AngelPeriod(45, 11, 3, 11, 7),
            AngelPeriod(46, 11, 8, 11, 12),
            AngelPeriod(47, 11, 13, 11, 17),
            AngelPeriod(48, 11, 18, 11, 22),
            AngelPeriod(49, 11, 23, 11, 27),
            AngelPeriod(50, 11, 28, 12, 2),
            AngelPeriod(51, 12, 3, 12, 7),
            AngelPeriod(52, 12, 8, 12, 12),
            AngelPeriod(53, 12, 13, 12, 16),
            AngelPeriod(54, 12, 17, 12, 21),
            AngelPeriod(55, 12, 22, 12, 26),
            AngelPeriod(56, 12, 27, 12, 31),
            AngelPeriod(57, 1, 1, 1, 5),
            AngelPeriod(58, 1, 6, 1, 10),
            AngelPeriod(59, 1, 11, 1, 15),
            AngelPeriod(60, 1, 16, 1, 20),
            AngelPeriod(61, 1, 21, 1, 25),
            AngelPeriod(62, 1, 26, 1, 30),
            AngelPeriod(63, 1, 31, 2, 4),
            AngelPeriod(64, 2, 5, 2, 9),
            AngelPeriod(65, 2, 10, 2, 14),
            AngelPeriod(66, 2, 15, 2, 19),
            AngelPeriod(67, 2, 20, 2, 24),
            AngelPeriod(68, 2, 25, 2, 29),
            AngelPeriod(69, 3, 1, 3, 5),
            AngelPeriod(70, 3, 6, 3, 10),
            AngelPeriod(71, 3, 11, 3, 15),
            AngelPeriod(72, 3, 16, 3, 20)
        )
    }
}