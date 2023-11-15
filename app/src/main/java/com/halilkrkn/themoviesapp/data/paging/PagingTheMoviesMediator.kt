package com.halilkrkn.themoviesapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.halilkrkn.themoviesapp.data.local.TheMoviesDatabase
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
import com.halilkrkn.themoviesapp.data.mappers.toTheMoviesEntity
import com.halilkrkn.themoviesapp.data.remote.api.TheMoviesApi
import kotlinx.coroutines.*
import retrofit2.HttpException

@OptIn(ExperimentalPagingApi::class)
class PagingTheMoviesMediator(
    private val db: TheMoviesDatabase,
    private val api: TheMoviesApi,
) : RemoteMediator<Int, TheMoviesEntity>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TheMoviesEntity>,
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            delay(2000L)
            val theMovies = api.getPopularMovies(
                page = loadKey,
                totalPages = state.config.pageSize
            )
            val theMoviesEntities = theMovies.theMoviesDtos.map { theMoviesDto ->
                theMoviesDto.toTheMoviesEntity()
            }
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.theMoviesDao().delete()
                }

                db.theMoviesDao().insert(theMoviesEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = theMovies.theMoviesDtos.isEmpty()
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}