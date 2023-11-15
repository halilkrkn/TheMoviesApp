package com.halilkrkn.themoviesapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.map
import com.google.common.truth.Truth.assertThat
import com.halilkrkn.themoviesapp.data.local.TheMoviesDatabase
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesFavoriteEntity
import com.halilkrkn.themoviesapp.data.paging.PagingTheMoviesMediator
import com.halilkrkn.themoviesapp.data.remote.api.TheMoviesApi
import com.halilkrkn.themoviesapp.data.remote.dto.TheMoviesAllDto
import com.halilkrkn.themoviesapp.data.remote.dto.detail.TheMoviesDetailDto
import com.halilkrkn.themoviesapp.domain.model.TheMovies
import com.halilkrkn.themoviesapp.domain.repository.TheMoviesRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`


class TheMoviesRepositoryImplTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var theMoviesRepository: TheMoviesRepository
    private lateinit var theMoviesApi: TheMoviesApi
    private lateinit var theMoviesDatabase: TheMoviesDatabase

    @Before
    fun setup() {
        theMoviesApi = mockk()
        theMoviesDatabase = mockk()

        theMoviesRepository = TheMoviesRepositoryImpl(theMoviesApi, theMoviesDatabase)
    }

    @Test
    fun `getAllTheMovies returns Pager`() {
        // Given
        val pager: Pager<Int, TheMoviesEntity> = theMoviesRepository.getAllTheMovies()

        // When
        // Then
        assertThat(pager).isNotNull()
    }

    @Test
    fun `getTheMoviesDetail returns TheMoviesDetailDto`() = runTest {

        //Given
        val movieId = 123
        val detailDto = mockk<TheMoviesDetailDto>()

        //When
        coEvery { theMoviesApi.getTheMoviesDetail(movieId) } returns detailDto

        //Then
        val result = theMoviesRepository.getTheMoviesDetail(movieId)
        assertThat(result).isEqualTo(detailDto)
    }

    @Test
    fun `getTheMoviesDetail should return a TheMoviesDetailDto`() = runTest {

        // Given
        val movieId = 123
        val expectedMovieDetail = mockk<TheMoviesDetailDto>()

        // When
        coEvery { theMoviesApi.getTheMoviesDetail(movieId) } returns expectedMovieDetail

        // Then
        val actualMovieDetail = theMoviesRepository.getTheMoviesDetail(movieId)
        assertThat(actualMovieDetail).isEqualTo(expectedMovieDetail)
    }

    @Test
    fun `searchTheMovies should return a TheMoviesAllDto`() = runTest {
        // Given
        val expectedMovieList = mockk<TheMoviesAllDto>()

        // When
        coEvery { theMoviesApi.searchTheMovies("query") } returns expectedMovieList

        // Then
        val actualMovieList = theMoviesRepository.searchTheMovies("query")
        assertThat(actualMovieList).isEqualTo(expectedMovieList)
    }
}