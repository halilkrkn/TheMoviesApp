package com.halilkrkn.themoviesapp.presentation.main.detail

import androidx.compose.runtime.mutableStateOf
import com.halilkrkn.themoviesapp.domain.model.TheMovies

data class TheMoviesDetailState(
    val isLoading: Boolean = false,
    val theMoviesDetail: TheMovies? = null,
    val error: String = "",
    val isFavorite: Boolean = false
)
