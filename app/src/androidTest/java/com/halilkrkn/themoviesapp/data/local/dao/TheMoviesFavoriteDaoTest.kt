package com.halilkrkn.themoviesapp.data.local.dao

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.halilkrkn.themoviesapp.data.local.TheMoviesDatabase
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesFavoriteEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@SmallTest
@HiltAndroidTest
class TheMoviesFavoriteDaoTest {


    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("the_movie_test_db")
    lateinit var database: TheMoviesDatabase

    private lateinit var dao: TheMoviesFavoriteDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.theMoviesFavoriteDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun givenFavoriteMovie_whenInsertFavoriteMovie_thenGetAllFavoriteMovie() = runBlocking {
        // Given
        val favoriteMovie = TheMoviesFavoriteEntity(
            id = 1,
            title = "The Movie",
            posterPath = "posterPath",
            backdropPath = "backdropPath",
            overview = "overview",
            releaseDate = "releaseDate",
            voteAverage = 1.0,
            voteCount = 1,
            popularity = 1.0,
            originalLanguage = "originalLanguage",
            originalTitle = "originalTitle",
            adult = false,
            video = false
        )


        // When
        dao.insert(favoriteMovie)

        // Then
        dao.getAllFavorite().first().let { favoriteMovies ->
            assertThat(favoriteMovies).contains(favoriteMovie)
        }
    }

    @Test
    fun givenFavoriteMovie_whenDeleteFavoriteMovie_thenGetAllFavoriteMovie() = runBlocking {
        // Given
        val favoriteMovie = TheMoviesFavoriteEntity(
            id = 1,
            title = "The Movie",
            posterPath = "posterPath",
            backdropPath = "backdropPath",
            overview = "overview",
            releaseDate = "releaseDate",
            voteAverage = 1.0,
            voteCount = 1,
            popularity = 1.0,
            originalLanguage = "originalLanguage",
            originalTitle = "originalTitle",
            adult = false,
            video = false
        )

        // When
        dao.insert(favoriteMovie)
        dao.delete(favoriteMovie)

        // Then
        dao.getAllFavorite().first().let { favoriteMovies ->
            assertThat(favoriteMovies).doesNotContain(favoriteMovie)
        }
    }

    @Test
    fun givenFavoriteMovie_whenSearchFavoriteMovie_thenGetSearchedFavoriteMovie() = runBlocking {
        // Given
        val favoriteMovie = TheMoviesFavoriteEntity(
            id = 1,
            title = "The Movie",
            posterPath = "posterPath",
            backdropPath = "backdropPath",
            overview = "overview",
            releaseDate = "releaseDate",
            voteAverage = 1.0,
            voteCount = 1,
            popularity = 1.0,
            originalLanguage = "originalLanguage",
            originalTitle = "originalTitle",
            adult = false,
            video = false
        )

        // When
        dao.insert(favoriteMovie)

        // Then
        dao.searchFavorite("The Movie").first().let { favoriteMovies ->
            assertThat(favoriteMovies).contains(favoriteMovie)
        }
    }

    @Test
    fun givenFavoriteMovie_whenSearchFavoriteMovie_thenGetEmptyList() = runBlocking {
        // Given
        val favoriteMovie = TheMoviesFavoriteEntity(
            id = 1,
            title = "The Movie",
            posterPath = "posterPath",
            backdropPath = "backdropPath",
            overview = "overview",
            releaseDate = "releaseDate",
            voteAverage = 1.0,
            voteCount = 1,
            popularity = 1.0,
            originalLanguage = "originalLanguage",
            originalTitle = "originalTitle",
            adult = false,
            video = false
        )

        // When
        dao.insert(favoriteMovie)

        // Then
        dao.searchFavorite("The Movie 2").first().let { favoriteMovies ->
            assertThat(favoriteMovies).doesNotContain(favoriteMovie)
        }
    }
}


