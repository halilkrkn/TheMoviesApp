package com.halilkrkn.themoviesapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.halilkrkn.themoviesapp.core.Constants.UNSPLASH_STARTING_PAGE_INDEX
import com.halilkrkn.themoviesapp.data.remote.api.TheMoviesApi
import com.halilkrkn.themoviesapp.data.remote.dto.TheMoviesDto
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TheMoviesSearchPagingSource @Inject constructor(
    private val tidingsApiService: TheMoviesApi,
    private val query: String
) : PagingSource<Int, TheMoviesDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TheMoviesDto> {

        val position = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
        return try {
            val response = tidingsApiService.searchTheMovies(
                page = position,
                totalPages = params.loadSize,
                query = query
            )
            val theMovies = response.theMoviesDtos

            LoadResult.Page(
                data = theMovies,
                prevKey = if ( position == UNSPLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if ( theMovies.isEmpty()) null else position + 1
            )
        } catch (e: IOException){
            LoadResult.Error(e)
        } catch (e: HttpException){
            LoadResult.Error(e)
        }



    }

    override fun getRefreshKey(state: PagingState<Int, TheMoviesDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}