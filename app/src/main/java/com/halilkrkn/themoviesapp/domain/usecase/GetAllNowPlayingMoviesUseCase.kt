package com.halilkrkn.themoviesapp.domain.usecase

import android.util.Log
import com.halilkrkn.themoviesapp.core.Constants.TAG
import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.data.mappers.toTheMovies
import com.halilkrkn.themoviesapp.data.remote.dto.explore.TheExplorerMovieDto
import com.halilkrkn.themoviesapp.data.remote.dto.explore.TheExplorerMovieListsDto
import com.halilkrkn.themoviesapp.domain.model.TheExplorerMovieLists
import com.halilkrkn.themoviesapp.domain.model.TheMovies
import com.halilkrkn.themoviesapp.domain.repository.TheMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllNowPlayingMoviesUseCase @Inject constructor(
    private val theMoviesRepository: TheMoviesRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<TheExplorerMovieDto>>>  = flow {
        try {
            emit(Resource.Loading())
            val theExplorerMovieListsDto = theMoviesRepository.getNowPlayingMovies()
            val response = theExplorerMovieListsDto.theExplorerMovieDtos
            Log.d(TAG, "invoke: ${response.first().title}")
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }
}