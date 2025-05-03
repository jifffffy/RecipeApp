package org.jiffy.press.ui.destinations.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jiffy.press.domain.usecase.GetTopRecommendationsUseCase

class HomeViewModel(
    private val getTopRecommendationsUseCase: GetTopRecommendationsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) : ViewModel() {
    private var _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    private fun startLoading() {
        _uiState.update { currentUiState ->
            currentUiState.copy(
                isLoading = true,
            )
        }
    }

    fun onRefresh() {
        startLoading()
        viewModelScope.launch(dispatcher) {
            val meals = getTopRecommendationsUseCase()
            if (meals.isSuccess) {
                _uiState.value = _uiState.value.copy(
                    meals = meals.getOrDefault(emptyList()),
                    isLoading = false
                )
            } else {
                _uiState.update {
                    it.copy(
                        error = meals.exceptionOrNull()?.message,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun requestScrollToTop(enabled: Boolean) {
        _uiState.update { currentUiState ->
            currentUiState.copy(
                requestScrollToTop = enabled,
            )
        }
    }
}