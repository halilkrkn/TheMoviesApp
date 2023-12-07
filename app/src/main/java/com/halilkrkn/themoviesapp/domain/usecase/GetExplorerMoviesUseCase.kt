package com.halilkrkn.themoviesapp.domain.usecase

import android.util.Log
import com.halilkrkn.themoviesapp.core.Constants.TAG
import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.data.remote.dto.explore.TheExplorerMovieDto
import com.halilkrkn.themoviesapp.domain.repository.TheMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetExplorerMoviesUseCase @Inject constructor(
    private val theMoviesRepository: TheMoviesRepository
) {

    suspend fun getNowPlayingMovies(): Flow<Resource<List<TheExplorerMovieDto>>>  = flow {
        try {
            emit(Resource.Loading())
            val theExplorerMovieListsDto = theMoviesRepository.getNowPlayingMovies()
            val response = theExplorerMovieListsDto.theExplorerMovieDtos
            Log.d("getNowPlayingMovies", "getNowPlayingMovies: $response")
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }
    suspend fun getPopularMovies(): Flow<Resource<List<TheExplorerMovieDto>>> = flow {
        try {
            emit(Resource.Loading())
            val theExplorerMovieListsDto = theMoviesRepository.getPopularMovies()
            val response = theExplorerMovieListsDto.theExplorerMovieDtos
            Log.d("getPopularMovies", "getPopularMovies: $response")
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }

    suspend fun getTopRatedMovies(): Flow<Resource<List<TheExplorerMovieDto>>> = flow {
        try {
            emit(Resource.Loading())
            val theExplorerMovieListsDto = theMoviesRepository.getTopRatedMovies()
            val response = theExplorerMovieListsDto.theExplorerMovieDtos
            Log.d("getTopRatedMovies", "getTopRatedMovies: $response")
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }

}