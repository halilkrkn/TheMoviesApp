package com.halilkrkn.themoviesapp.presentation.main.explore

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.data.mappers.toTheMovies
import com.halilkrkn.themoviesapp.domain.usecase.TheMoviesUseCases
import com.halilkrkn.themoviesapp.presentation.main.explore.state.TheExplorerMoviesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TheExplorerMoviesViewModel @Inject constructor(
    private val theMoviesUseCases: TheMoviesUseCases,
) : ViewModel() {

    private val _state =
        mutableStateOf<TheExplorerMoviesState>(TheExplorerMoviesState())
    val state: State<TheExplorerMoviesState> = _state

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()


    private var movieJob: Job? = null

    init {
        getTheExploreNowPlayingMovies()
        getTheExplorePopularMovies()
    }

    fun onRefresh() {
        _isRefreshing.value = true
        getTheExploreNowPlayingMovies()
        getTheExplorePopularMovies()
        _isRefreshing.value = false
    }
    private fun getTheExploreNowPlayingMovies() {
        _isLoading.value = true
        movieJob?.cancel()
        movieJob = viewModelScope.launch(Dispatchers.IO) {
            theMoviesUseCases.getAllNowPlayingMoviesUseCase.invoke().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = TheExplorerMoviesState(
                            isLoading = false,
                            theExplorerMovies = result.data?.map { theExplorerMovies ->
                               theExplorerMovies.toTheMovies()
                            }?: emptyList(),
                        )
                    }

                    is Resource.Error -> {
                        _state.value = TheExplorerMoviesState(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = TheExplorerMoviesState(
                            isLoading = true,
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }


    private fun getTheExplorePopularMovies() {
        _isLoading.value = true
        movieJob?.cancel()
        movieJob = viewModelScope.launch(Dispatchers.IO) {
            theMoviesUseCases.getAllPopularMoviesUseCase.invoke().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = TheExplorerMoviesState(
                            isLoading = false,
                            theExplorerMovies = result.data?.map { theExplorerMovies ->
                                theExplorerMovies.toTheMovies()
                            }?: emptyList(),
                        )
                    }

                    is Resource.Error -> {
                        _state.value = TheExplorerMoviesState(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = TheExplorerMoviesState(
                            isLoading = true,
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }
}