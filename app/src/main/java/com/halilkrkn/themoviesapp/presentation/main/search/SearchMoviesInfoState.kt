package com.halilkrkn.themoviesapp.presentation.main.search

import com.halilkrkn.themoviesapp.domain.model.TheMovies

data class SearchMoviesInfoState(
    val searchMovies: List<TheMovies> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)