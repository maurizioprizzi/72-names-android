package com.nomes72.app.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomes72.app.domain.model.DailyInsight
import com.nomes72.app.domain.model.SacredName
import com.nomes72.app.domain.usecase.GetAllNamesUseCase
import com.nomes72.app.domain.usecase.GetDailyNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val allNames: List<SacredName> = emptyList(),
    val filteredNames: List<SacredName> = emptyList(),
    val searchQuery: String = "",
    val isSearching: Boolean = false,
    val isLoading: Boolean = true,
    val dailyInsight: DailyInsight? = null
) {
    val pages: List<List<SacredName>>
        get() = filteredNames.chunked(8)

    val totalPages: Int
        get() = pages.size
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    getAllNamesUseCase: GetAllNamesUseCase,
    private val getDailyNameUseCase: GetDailyNameUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val _dailyInsight = MutableStateFlow<DailyInsight?>(null)

    init {
        loadDailyName()
    }

    val uiState: StateFlow<HomeUiState> = combine(
        getAllNamesUseCase(),
        _searchQuery,
        _dailyInsight
    ) { names, query, daily ->
        val filtered = if (query.isBlank()) {
            names
        } else {
            names.filter { name ->
                name.meaning.contains(query, ignoreCase = true) ||
                        name.transliteration.contains(query, ignoreCase = true)
            }
        }

        HomeUiState(
            allNames = names,
            filteredNames = filtered,
            searchQuery = query,
            isSearching = query.isNotBlank(),
            isLoading = false,
            dailyInsight = daily
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState()
    )

    private fun loadDailyName() {
        viewModelScope.launch {
            try {
                _dailyInsight.value = getDailyNameUseCase()
            } catch (_: Exception) {
                // Silent fail — card simplesmente não aparece
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun onClearSearch() {
        _searchQuery.value = ""
    }
}