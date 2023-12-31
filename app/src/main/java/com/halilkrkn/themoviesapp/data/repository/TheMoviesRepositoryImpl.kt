package com.halilkrkn.themoviesapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.halilkrkn.themoviesapp.data.local.TheMoviesDatabase
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesFavoriteEntity
import com.halilkrkn.themoviesapp.data.paging.PagingTheMoviesMediator
import com.halilkrkn.themoviesapp.data.remote.api.TheMoviesApi
import com.halilkrkn.themoviesapp.data.remote.dto.TheMoviesAllDto
import com.halilkrkn.themoviesapp.data.remote.dto.detail.TheMoviesDetailDto
import com.halilkrkn.themoviesapp.data.remote.dto.explore.TheExplorerMovieListsDto
import com.halilkrkn.themoviesapp.data.remote.dto.trending.TrendingMoviesDtos
import com.halilkrkn.themoviesapp.domain.repository.TheMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class TheMoviesRepositoryImpl @Inject constructor(
    private val theMoviesApi: TheMoviesApi,
    private val theMoviesDatabase: TheMoviesDatabase
) : TheMoviesRepository {

    // Network Operations
    override fun getAllTheMovies(): Pager<Int, TheMoviesEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,             // Her sayfa 20 öğe içerecek
                prefetchDistance = 5,       // Mevcut sayfanın sonuna gelmeden önce kaç sayfa önceden yüklenmeli?
                initialLoadSize = 20 * 2,   // İlk yüklenme sırasında kaç öğe alınmalı? (pageSize * 2 gibi bir değer düşünebilirsiniz)
                maxSize = 100,              // Bellekte saklanacak maksimum öğe sayısı
                enablePlaceholders = false  // Yer tutucu öğeler kullanılmasın (gerçek veri hemen yüklensin)
            ),
            remoteMediator = PagingTheMoviesMediator(
                theMoviesDatabase,
                theMoviesApi
            ),
            pagingSourceFactory = { theMoviesDatabase.theMoviesDao().getAllTheMovies() }
        )
    }

    override suspend fun getTheMoviesDetail(id: Int): TheMoviesDetailDto {
        return theMoviesApi.getTheMoviesDetail(id)
    }

    override suspend fun searchTheMovies(query: String): TheMoviesAllDto {
         return theMoviesApi.searchTheMovies(query)

    }

    override suspend fun getNowPlayingMovies(): TheExplorerMovieListsDto {
        return theMoviesApi.getNowPlayingMovies()
    }

    override suspend fun getPopularMovies(): TheExplorerMovieListsDto {
        return theMoviesApi.getPopularMovies()
    }

    override suspend fun getTopRatedMovies(): TheExplorerMovieListsDto {
        return theMoviesApi.getTopRatedMovies()
    }

    override suspend fun getUpcomingMovies(): TheExplorerMovieListsDto {
        return theMoviesApi.getUpcomingMovies()
    }

    override suspend fun getTrendingDailyMovies(): TrendingMoviesDtos {
        return theMoviesApi.getTrendingDailyMovies()
    }

    override suspend fun getTrendingWeeklyMovies(): TrendingMoviesDtos {
        return theMoviesApi.getTrendingWeeklyMovies()
    }

    // Database Operations
    override suspend fun insertFavorite(theMovies: TheMoviesFavoriteEntity) {
        return theMoviesDatabase.theMoviesFavoriteDao().insert(theMovies)
    }

    override suspend fun deleteFavorite(theMovies: TheMoviesFavoriteEntity) {
        theMoviesDatabase.theMoviesFavoriteDao().delete(theMovies)
    }

    override fun getAllFavorites(userId: String): Flow<List<TheMoviesFavoriteEntity>> {
        return theMoviesDatabase.theMoviesFavoriteDao().getAllFavorite(userId)
    }

    override fun searchFavorite(searchQuery: String): Flow<List<TheMoviesFavoriteEntity>> {
        return theMoviesDatabase.theMoviesFavoriteDao().searchFavorite(searchQuery)
    }
}