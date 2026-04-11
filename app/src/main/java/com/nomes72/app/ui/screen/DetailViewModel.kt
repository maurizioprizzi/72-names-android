package com.nomes72.app.ui.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomes72.app.domain.model.SacredName
import com.nomes72.app.domain.usecase.GetNameByNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailUiState(
    val name: SacredName? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getNameByNumberUseCase: GetNameByNumberUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState

    init {
        val number = savedStateHandle.get<Int>("nameNumber") ?: 1
        loadName(number)
    }

    private fun loadName(number: Int) {
        viewModelScope.launch {
            try {
                val name = getNameByNumberUseCase(number)
                _uiState.value = DetailUiState(
                    name = name,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = DetailUiState(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun getShareText(): String {
        val name = _uiState.value.name ?: return ""
        return buildString {
            appendLine("✡ ${name.transliteration} — ${name.meaning}")
            appendLine()
            appendLine(name.hebrewLetters)
            appendLine()
            appendLine(name.meditation)
            appendLine()
            appendLine("📖 ${name.torahVerse}")
            appendLine()
            appendLine("— 72 Nomes Sagrados")
        }
    }
}