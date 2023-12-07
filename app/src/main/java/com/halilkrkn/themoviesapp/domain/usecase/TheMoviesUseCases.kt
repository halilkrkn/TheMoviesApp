package com.halilkrkn.themoviesapp.domain.usecase

data class TheMoviesUseCases(
    val getAllTheMoviesUseCase: GetAllTheMoviesUseCase,
    val getTheMoviesDetailUseCase: GetTheMoviesDetailUseCase,
    val getSearchTheMoviesUseCase: GetSearchTheMoviesUseCase,
    val getTheMoviesFavoriteUseCase: GetTheMoviesFavoriteUseCase,
    val getExplorerMoviesUseCase: GetExplorerMoviesUseCase,
)
