package com.halilkrkn.themoviesapp.presentation.main.explore

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.data.mappers.toTheMovies
import com.halilkrkn.themoviesapp.domain.usecase.TheMoviesUseCases
import com.halilkrkn.themoviesapp.presentation.main.explore.state.TheExplorerNowPlayingMoviesState
import com.halilkrkn.themoviesapp.presentation.main.explore.state.TheExplorerPopularMoviesState
import com.halilkrkn.themoviesapp.presentation.main.explore.state.TheExplorerTopRatedMoviesState
import com.halilkrkn.themoviesapp.presentation.main.explore.state.TheExplorerUpcomingMoviesState
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

    private val _stateNowPlaying =
        mutableStateOf<TheExplorerNowPlayingMoviesState>(TheExplorerNowPlayingMoviesState())
    val stateNowPlaying: State<TheExplorerNowPlayingMoviesState> = _stateNowPlaying

    private val _statePopular = mutableStateOf<TheExplorerPopularMoviesState>(
        TheExplorerPopularMoviesState()
    )
    val statePopular: State<TheExplorerPopularMoviesState> = _statePopular

    private val _stateTopRated = mutableStateOf<TheExplorerTopRatedMoviesState>(
        TheExplorerTopRatedMoviesState()
    )
    val stateTopRated: State<TheExplorerTopRatedMoviesState> = _stateTopRated

    private val _stateUpComing = mutableStateOf<TheExplorerUpcomingMoviesState>(
        TheExplorerUpcomingMoviesState()
    )
    val stateUpComing: State<TheExplorerUpcomingMoviesState> = _stateUpComing


    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()


    private var movieJob: Job? = null

    init {
        getTheExploreNowPlayingMovies()
        getTheExplorePopularMovies()
        getExploreTopRatedTheMovies()
        getExploreUpcomingTheMovies()
    }

    fun onRefresh() {
        _isRefreshing.value = true
        getTheExploreNowPlayingMovies()
        getTheExplorePopularMovies()
        getExploreTopRatedTheMovies()
        getExploreUpcomingTheMovies()
        _isRefreshing.value = false
    }

    private fun getTheExploreNowPlayingMovies() {
        _isLoading.value = true
        movieJob?.cancel()
        movieJob = viewModelScope.launch(Dispatchers.IO) {
            theMoviesUseCases.getExplorerMoviesUseCase.getNowPlayingMovies().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _stateNowPlaying.value = TheExplorerNowPlayingMoviesState(
                            isLoading = false,
                            theExplorerMovies = result.data?.map {
                                it.toTheMovies()
                            } ?: emptyList(),
                        )
                    }

                    is Resource.Error -> {
                        _stateNowPlaying.value = TheExplorerNowPlayingMoviesState(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }

                    is Resource.Loading -> {
                        _stateNowPlaying.value = TheExplorerNowPlayingMoviesState(
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
            theMoviesUseCases.getExplorerMoviesUseCase.getPopularMovies().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _statePopular.value = TheExplorerPopularMoviesState(
                            isLoading = false,
                            theExplorerMovies = result.data?.map {
                                it.toTheMovies()
                            } ?: emptyList(),
                        )
                    }

                    is Resource.Error -> {
                        _statePopular.value = TheExplorerPopularMoviesState(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }

                    is Resource.Loading -> {
                        _statePopular.value = TheExplorerPopularMoviesState(
                            isLoading = true,
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }


    private fun getExploreTopRatedTheMovies() {
        _isLoading.value = true
        movieJob?.cancel()
        movieJob = viewModelScope.launch(Dispatchers.IO) {
            theMoviesUseCases.getExplorerMoviesUseCase.getTopRatedMovies().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _stateTopRated.value = TheExplorerTopRatedMoviesState(
                            isLoading = false,
                            theExplorerMovies = result.data?.map {
                                it.toTheMovies()
                            } ?: emptyList(),
                            )
                    }

                    is Resource.Error -> {
                        _stateTopRated.value = TheExplorerTopRatedMoviesState(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }

                    is Resource.Loading -> {
                        _stateTopRated.value = TheExplorerTopRatedMoviesState(
                            isLoading = true,
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }


    private fun getExploreUpcomingTheMovies() {
        _isLoading.value = true
        movieJob?.cancel()
        movieJob = viewModelScope.launch(Dispatchers.IO) {
            theMoviesUseCases.getExplorerMoviesUseCase.getUpcomingMovies().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _stateUpComing.value = TheExplorerUpcomingMoviesState(
                            isLoading = false,
                            theExplorerMovies = result.data?.map {
                                it.toTheMovies()
                            } ?: emptyList(),
                        )
                    }

                    is Resource.Error -> {
                        _stateUpComing.value = TheExplorerUpcomingMoviesState(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }

                    is Resource.Loading -> {
                        _stateUpComing.value = TheExplorerUpcomingMoviesState(
                            isLoading = true,
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }
}