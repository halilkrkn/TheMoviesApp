package com.halilkrkn.themoviesapp.domain.usecase

import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.data.remote.dto.explore.TheExplorerMovieDto
import com.halilkrkn.themoviesapp.domain.repository.TheMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllPopularMoviesUseCase @Inject constructor(
    private val theMoviesRepository: TheMoviesRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<TheExplorerMovieDto>>> = flow {
        try {
            emit(Resource.Loading())
            val theExplorerMovieListsDto = theMoviesRepository.getPopularMovies()
            val response = theExplorerMovieListsDto.theExplorerMovieDtos
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }
}