package com.halilkrkn.themoviesapp.presentation.main.trending

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.data.mappers.toTrendingMovies
import com.halilkrkn.themoviesapp.domain.usecase.TheMoviesUseCases
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
class TrendingMoviesViewModel @Inject constructor(
    private val theMoviesUseCases: TheMoviesUseCases,
) : ViewModel() {

    private val _state = mutableStateOf<TrendingMoviesState>(TrendingMoviesState())
    val state: State<TrendingMoviesState> = _state

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()


    private var movieJob: Job? = null

    init {
        getDailyMovies()
    }
    fun getDailyMovies() {
        getTheTrendingMovies()
    }

    fun onRefreshDailyMovies() {
        _isRefreshing.value = true
        getTheTrendingMovies()
        _isRefreshing.value = false
    }

    private fun getTheTrendingMovies() {
        _isLoading.value = true
        movieJob?.cancel()
        movieJob = viewModelScope.launch(Dispatchers.IO) {
            theMoviesUseCases.getAllTrendingMoviesUseCase.getTrendingDailyMovies()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = TrendingMoviesState(
                                isLoading = false,
                                theTrendingMovies = result.data?.map { trendingMoviesDto ->
                                    trendingMoviesDto.toTrendingMovies()
                                } ?: emptyList(),
                            )
                        }

                        is Resource.Error -> {
                            _state.value = TrendingMoviesState(
                                isLoading = false,
                                error = result.message ?: "An unexpected error occurred"
                            )
                        }

                        is Resource.Loading -> {
                            _state.value = TrendingMoviesState(
                                isLoading = true,
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }

    fun getWeeklyMovies() {
        getTrendingWeeklyMovies()
    }

    fun onRefreshWeeklyMovies() {
        _isRefreshing.value = true
        getTrendingWeeklyMovies()
        _isRefreshing.value = false
    }
    private fun getTrendingWeeklyMovies() {
        _isLoading.value = true
        movieJob?.cancel()
        movieJob = viewModelScope.launch(Dispatchers.IO) {
            theMoviesUseCases.getAllTrendingMoviesUseCase.getTrendingWeeklyMovies()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = TrendingMoviesState(
                                isLoading = false,
                                theTrendingMovies = result.data?.map { trendingMoviesDto ->
                                    trendingMoviesDto.toTrendingMovies()
                                } ?: emptyList(),
                            )
                        }

                        is Resource.Error -> {
                            _state.value = TrendingMoviesState(
                                isLoading = false,
                                error = result.message ?: "An unexpected error occurred"
                            )
                        }

                        is Resource.Loading -> {
                            _state.value = TrendingMoviesState(
                                isLoading = true,
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }
}
