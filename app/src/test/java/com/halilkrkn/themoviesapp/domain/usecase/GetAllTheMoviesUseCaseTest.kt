package com.halilkrkn.themoviesapp.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.Pager
import com.google.common.truth.Truth.assertThat
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
import com.halilkrkn.themoviesapp.domain.repository.TheMoviesRepository
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetAllTheMoviesUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var theMoviesRepository: TheMoviesRepository

    private lateinit var getAllTheMoviesUseCase: GetAllTheMoviesUseCase

    val dummyMoviesPager: Pager<Int, TheMoviesEntity> = mockk()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        getAllTheMoviesUseCase = GetAllTheMoviesUseCase(theMoviesRepository)
    }

    @Test
    fun `invoke should return Pager with movies`() = runTest {
        //Given

        //When
        `when`(theMoviesRepository.getAllTheMovies()).thenReturn(dummyMoviesPager)

        //Then
        val result = getAllTheMoviesUseCase.invoke()
        assertThat(result).isEqualTo(dummyMoviesPager)
    }

    @Test
    fun `invoke should call theMoviesRepository getAllTheMovies()`() = runTest {
        //Given
        getAllTheMoviesUseCase()

        //When
        `when`(theMoviesRepository.getAllTheMovies()).thenReturn(dummyMoviesPager)

        //Then
        verify(theMoviesRepository).getAllTheMovies()
    }
}