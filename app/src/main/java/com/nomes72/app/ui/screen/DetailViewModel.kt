package com.nomes72.app.ui.screen

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomes72.app.R
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
    val error: String? = null,
    val hasPrevious: Boolean = false,
    val hasNext: Boolean = false
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getNameByNumberUseCase: GetNameByNumberUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState

    private var currentNumber: Int = savedStateHandle.get<Int>("nameNumber") ?: 1

    init {
        loadName(currentNumber)
    }

    private fun loadName(number: Int) {
        viewModelScope.launch {
            _uiState.value = DetailUiState(isLoading = true)
            try {
                val name = getNameByNumberUseCase(number)
                currentNumber = number
                _uiState.value = DetailUiState(
                    name = name,
                    isLoading = false,
                    hasPrevious = number > 1,
                    hasNext = number < 72
                )
            } catch (e: Exception) {
                _uiState.value = DetailUiState(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun onPrevious() {
        if (currentNumber > 1) loadName(currentNumber - 1)
    }

    fun onNext() {
        if (currentNumber < 72) loadName(currentNumber + 1)
    }

    fun getShareText(context: Context): String {
        val name = _uiState.value.name ?: return ""
        return buildString {
            appendLine(context.getString(R.string.share_header, name.transliteration, name.meaning))
            appendLine()
            appendLine(name.hebrewLetters)
            appendLine()
            appendLine(name.meditation)
            appendLine()
            appendLine("📖 ${name.torahVerse}")
            appendLine()
            appendLine(context.getString(R.string.share_footer))
        }
    }
}