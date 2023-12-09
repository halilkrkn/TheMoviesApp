package com.halilkrkn.themoviesapp.presentation.main.trending

import com.halilkrkn.themoviesapp.domain.model.TheMovies
import com.halilkrkn.themoviesapp.domain.model.TheTrendingMovies

data class TrendingMoviesState(
    val isLoading: Boolean = false,
    val theTrendingMovies: List<TheTrendingMovies> = emptyList(),
    val error: String = "",
)
