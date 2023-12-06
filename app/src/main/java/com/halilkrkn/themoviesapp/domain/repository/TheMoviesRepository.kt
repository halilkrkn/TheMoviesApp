package com.halilkrkn.themoviesapp.domain.repository

import androidx.paging.Pager
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesFavoriteEntity
import com.halilkrkn.themoviesapp.data.remote.dto.TheMoviesAllDto
import com.halilkrkn.themoviesapp.data.remote.dto.detail.TheMoviesDetailDto
import com.halilkrkn.themoviesapp.data.remote.dto.explore.TheExplorerMovieDto
import com.halilkrkn.themoviesapp.data.remote.dto.explore.TheExplorerMovieListsDto
import kotlinx.coroutines.flow.Flow

interface TheMoviesRepository {

    // Network Operations
    fun getAllTheMovies(): Pager<Int, TheMoviesEntity>
    suspend fun getTheMoviesDetail(id: Int): TheMoviesDetailDto
    suspend fun searchTheMovies(query: String): TheMoviesAllDto

    suspend fun getNowPlayingMovies(): TheExplorerMovieListsDto
    suspend fun getPopularMovies(): TheExplorerMovieListsDto
    suspend fun getTopRatedMovies(): TheExplorerMovieListsDto
    suspend fun getUpcomingMovies(): TheExplorerMovieListsDto

    // Database Operations
    suspend fun insertFavorite(theMovies: TheMoviesFavoriteEntity)
    suspend fun deleteFavorite(theMovies: TheMoviesFavoriteEntity)
    fun getAllFavorites(userId: String): Flow<List<TheMoviesFavoriteEntity>>
    fun searchFavorite(searchQuery: String): Flow<List<TheMoviesFavoriteEntity>>

}

