package com.halilkrkn.themoviesapp.domain.usecase

import android.util.Log
import com.halilkrkn.themoviesapp.core.Resource
import com.halilkrkn.themoviesapp.data.remote.dto.trending.TrendingMoviesDto
import com.halilkrkn.themoviesapp.domain.repository.TheMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllTrendingMoviesUseCase @Inject constructor(
    private val theMoviesRepository: TheMoviesRepository,
) {
    suspend fun getTrendingDailyMovies(): Flow<Resource<List<TrendingMoviesDto>>> = flow {
        try {
            emit(Resource.Loading())
            val theTrendingMovies = theMoviesRepository.getTrendingDailyMovies()
            val response = theTrendingMovies.trendingMovieDtos
            Log.d("getTrendingMovies", "getTrendingMovies: $response")
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }

    suspend fun getTrendingWeeklyMovies(): Flow<Resource<List<TrendingMoviesDto>>> = flow {
        try {
            emit(Resource.Loading())
            val theTrendingMovies = theMoviesRepository.getTrendingWeeklyMovies()
            val response = theTrendingMovies.trendingMovieDtos
            Log.d("getTrendingMovies", "getTrendingMovies: $response")
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }
}