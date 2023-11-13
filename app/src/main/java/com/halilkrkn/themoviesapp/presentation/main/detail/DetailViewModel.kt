package com.halilkrkn.themoviesapp.presentation.main.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halilkrkn.themoviesapp.core.Constants.MOVIE_ID
import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.data.mappers.toTheMovies
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
class DetailViewModel @Inject constructor(
    private val theMoviesUseCases: TheMoviesUseCases,
): ViewModel() {

    private val _state = mutableStateOf<TheMoviesDetailState>(TheMoviesDetailState())
    val state: State<TheMoviesDetailState> = _state

    private val _isLoading = MutableStateFlow(false)
    val isLoading =  _isLoading.asStateFlow()

    fun onRefresh(movieId: Int) {
        getTheMoviesDetail(movieId)
    }

    private var movieJob: Job? = null
    private fun getTheMoviesDetail(movieId: Int) {
        _isLoading.value = true
        movieJob?.cancel()
        movieJob = viewModelScope.launch(Dispatchers.IO) {
            theMoviesUseCases.getTheMoviesDetailUseCase(movieId).onEach { result ->
                when(result) {
                    is Resource.Success -> {
                        _state.value = TheMoviesDetailState(
                            isLoading = false,
                            theMoviesDetail = result.data
                        )
                    }
                    is Resource.Error -> {
                        _state.value = TheMoviesDetailState(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occured"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = TheMoviesDetailState(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }
}