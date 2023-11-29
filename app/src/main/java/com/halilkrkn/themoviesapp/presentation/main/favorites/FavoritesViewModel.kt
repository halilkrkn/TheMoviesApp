package com.halilkrkn.themoviesapp.presentation.main.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.data.mappers.toTheMovies
import com.halilkrkn.themoviesapp.data.mappers.toTheMoviesFavoriteEntity
import com.halilkrkn.themoviesapp.domain.model.TheMovies
import com.halilkrkn.themoviesapp.domain.usecase.TheMoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val theMoviesUseCases: TheMoviesUseCases,
    private val firebaseUser: FirebaseAuth?
) : ViewModel() {

    val userId = firebaseUser?.currentUser?.uid.toString()

    private val _state = mutableStateOf<TheMoviesFavoriteState>(TheMoviesFavoriteState())
    val state: State<TheMoviesFavoriteState> = _state

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _searchQuery = mutableStateOf<String>("")
    val searchQuery: State<String> = _searchQuery

    private var movieJob: Job? = null

    init {
        getTheMoviesFavoriteMovie(userId)
    }

    fun onRefresh(userId: String) {
        getTheMoviesFavoriteMovie(userId)
    }

    private fun getTheMoviesFavoriteMovie(userId: String) {
        _isLoading.value = true
        movieJob?.cancel()
        movieJob = viewModelScope.launch(Dispatchers.IO) {
            theMoviesUseCases.getTheMoviesFavoriteUseCase.getAllFavorites(userId).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = TheMoviesFavoriteState(
                            isLoading = false,
                            theMovies = result.data ?: emptyList()
                        )
                    }

                    is Resource.Error -> {
                        _state.value = TheMoviesFavoriteState(
                            isLoading = false,
                            error = result.message ?: "An unexpected error occured"
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = TheMoviesFavoriteState(
                            isLoading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
        _isLoading.value = false
    }

    fun onDeleteFavoritesMovie(theMovies: TheMovies) {
        deleteFavoriteMovie(theMovies)
    }

    private fun deleteFavoriteMovie(theMovies: TheMovies) {
        viewModelScope.launch(Dispatchers.IO) {
            theMoviesUseCases.getTheMoviesFavoriteUseCase.favoriteDelete(theMovies.toTheMoviesFavoriteEntity())
        }
    }

    fun onInsertFavoritesMovie(theMovies: TheMovies) {
        insertFavoriteMovie(theMovies)
    }

    private fun insertFavoriteMovie(theMovies: TheMovies) {
        viewModelScope.launch(Dispatchers.IO) {
            theMoviesUseCases.getTheMoviesFavoriteUseCase.favoriteInsert(theMovies.toTheMoviesFavoriteEntity())
        }
    }


    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            theMoviesUseCases.getTheMoviesFavoriteUseCase.searchFavorite(query)
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = TheMoviesFavoriteState(
                                isLoading = false,
                                theMovies = result.data ?: emptyList()
                            )
                        }

                        is Resource.Error -> {
                            _state.value = TheMoviesFavoriteState(
                                isLoading = false,
                                error = result.message ?: "An unexpected error occured"
                            )
                        }

                        is Resource.Loading -> {
                            _state.value = TheMoviesFavoriteState(
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

}