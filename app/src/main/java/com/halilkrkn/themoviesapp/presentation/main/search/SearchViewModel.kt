package com.halilkrkn.themoviesapp.presentation.main.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.data.mappers.toTheMovies
import com.halilkrkn.themoviesapp.domain.usecase.TheMoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val theMoviesUseCases: TheMoviesUseCases,
) : ViewModel() {


    private val _searchQuery = mutableStateOf<String>("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(SearchMoviesInfoState())
    val state: State<SearchMoviesInfoState> = _state

    private var searchJob: Job? = null
    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            theMoviesUseCases.getSearchTheMoviesUseCase(query)
                .onEach { result ->
                    when(result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                searchMovies = result.data?.map {
                                    it.toTheMovies()
                                } ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                isLoading = false
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }
}