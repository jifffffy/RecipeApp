package org.jiffy.press.ui.destinations.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jiffy.press.domain.model.meal.Meal
import org.jiffy.press.domain.usecase.GetMealsByTextUseCase

class SearchViewModel(
    private val getMealsByTextUseCase: GetMealsByTextUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {
    private val _uiState: MutableStateFlow<SearchUIState> = MutableStateFlow(SearchUIState())
    val uiState = _uiState.asStateFlow()

    private val _searchText = MutableStateFlow("")


    init {
        initLoading()
    }

    private suspend fun fetchItems(query: String): List<Meal> {
        if (query.isEmpty()) return emptyList()
        startLoading()
        return getMealsByTextUseCase(query).getOrNull() ?: emptyList()
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private fun initLoading() = viewModelScope.launch(dispatcher) {
        _searchText.debounce(500)
            .distinctUntilChanged()
            .flatMapLatest { text ->
                flow {
                    val results = fetchItems(text)
                    emit(results)
                }
            }
            .catch { error ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            }
            .collect { results ->
                _uiState.update {
                    it.copy(
                        results = results,
                        isLoading = false,
                        success = true,
                    )
                }
            }
    }

    fun onSearchTextChanged(text: String) {
        _uiState.update { currentUiState ->
            currentUiState.copy(
                searchText = text
            )
        }
        viewModelScope.launch(dispatcher) {
            _searchText.emit(text)
        }
    }

    private fun startLoading() {
        _uiState.update { currentUiState ->
            currentUiState.copy(
                isLoading = true,
            )
        }
    }
}