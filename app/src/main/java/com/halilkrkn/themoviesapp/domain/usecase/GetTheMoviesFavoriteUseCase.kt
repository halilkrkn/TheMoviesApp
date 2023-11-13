package com.halilkrkn.themoviesapp.domain.usecase

import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesFavoriteEntity
import com.halilkrkn.themoviesapp.data.mappers.toTheMovies
import com.halilkrkn.themoviesapp.domain.model.TheMovies
import com.halilkrkn.themoviesapp.domain.repository.TheMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTheMoviesFavoriteUseCase @Inject constructor(
    private val theMoviesRepository: TheMoviesRepository,
) {
    suspend fun favoriteInsert(theMovies: TheMoviesFavoriteEntity) =
        theMoviesRepository.insertFavorite(theMovies)

    suspend fun favoriteDelete(theMovies: TheMoviesFavoriteEntity) = theMoviesRepository.deleteFavorite(theMovies)

    fun getAllFavorites(): Flow<Resource<List<TheMovies>>> = flow {
        emit(Resource.Loading())
        val response = theMoviesRepository.getAllFavorites().first().map { it.toTheMovies() }
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "An unexpected error occurred"))
    }

    fun searchFavorite(searchQuery: String): Flow<Resource<List<TheMovies>>> = flow {
        emit(Resource.Loading())
        val response = theMoviesRepository.searchFavorite(searchQuery).first().map { it.toTheMovies() }
        emit(Resource.Success(response))
    }.catch {
        emit(Resource.Error(it.localizedMessage ?: "An unexpected error occurred"))
    }
}
