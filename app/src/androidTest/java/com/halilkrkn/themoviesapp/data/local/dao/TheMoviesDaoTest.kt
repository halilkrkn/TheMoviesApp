package com.halilkrkn.themoviesapp.data.local.dao

import androidx.paging.PagingSource
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.halilkrkn.themoviesapp.data.local.TheMoviesDatabase
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
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
class TheMoviesDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Inject
    @Named("the_movie_test_db")
    lateinit var database: TheMoviesDatabase

    private lateinit var dao: TheMoviesDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.theMoviesDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun givenTheMovies_whenInsertTheMovies_thenGetAllTheMovies() = runBlocking {
        // Given
        val theMovies = listOf(
            TheMoviesEntity(
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
                video = false,
                userId = "userId"
            ),
            TheMoviesEntity(
                id = 2,
                title = "The Movie 2",
                posterPath = "posterPath 2",
                backdropPath = "backdropPath 2",
                overview = "overview 2",
                releaseDate = "releaseDate 2",
                voteAverage = 2.0,
                voteCount = 2,
                popularity = 2.0,
                originalLanguage = "originalLanguage 2",
                originalTitle = "originalTitle 2",
                adult = false,
                video = false,
                userId = "userId"

            ),
            TheMoviesEntity(
                id = 3,
                title = "The Movie 3",
                posterPath = "posterPath 3",
                backdropPath = "backdropPath 3",
                overview = "overview 3",
                releaseDate = "releaseDate 3",
                voteAverage = 3.0,
                voteCount = 3,
                popularity = 3.0,
                originalLanguage = "originalLanguage 3",
                originalTitle = "originalTitle 3",
                adult = false,
                video = false,
                userId = "userId"

            )
        )

        // When
        dao.insert(theMovies)
        dao.getAllTheMovies()
        assertThat(theMovies).containsAnyIn(
            PagingSource.LoadResult.Page(
                data = theMovies,
                prevKey = null,
                nextKey = null
            )
        )

    }

    @Test
    fun givenTheMovies_whenInsertTheMovies_thenGetSearchedTheMovies() = runBlocking {
        // Given
        val theMovies = listOf(
            TheMoviesEntity(
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
                video = false,
                userId = "userId"

            ),
            TheMoviesEntity(
                id = 2,
                title = "The Movie 2",
                posterPath = "posterPath 2",
                backdropPath = "backdropPath 2",
                overview = "overview 2",
                releaseDate = "releaseDate 2",
                voteAverage = 2.0,
                voteCount = 2,
                popularity = 2.0,
                originalLanguage = "originalLanguage 2",
                originalTitle = "originalTitle 2",
                adult = false,
                video = false,
                userId = "userId"

            )
        )

        // When
        dao.insert(theMovies)
        dao.searchSavedName("The Movie").first().let { theMovies ->
            assertThat(theMovies).containsAnyIn(
                PagingSource.LoadResult.Page(
                    data = theMovies,
                    prevKey = null,
                    nextKey = null
                )
            )
        }
    }

    @Test
    fun givenTheMovies_whenInsertTheMovies_thenGetEmptyList() = runBlocking {
        // Given
        val theMovies = listOf(
            TheMoviesEntity(
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
                video = false,
                userId = "userId"

            ),
            TheMoviesEntity(
                id = 2,
                title = "The Movie 2",
                posterPath = "posterPath 2",
                backdropPath = "backdropPath 2",
                overview = "overview 2",
                releaseDate = "releaseDate 2",
                voteAverage = 2.0,
                voteCount = 2,
                popularity = 2.0,
                originalLanguage = "originalLanguage 2",
                originalTitle = "originalTitle 2",
                adult = false,
                video = false,
                userId = "userId"

            )
        )

        // When
        dao.insert(theMovies)
        dao.searchSavedName("The Movie 3").first().let { theMovies ->
            assertThat(theMovies).doesNotContain(
                PagingSource.LoadResult.Page(
                    data = theMovies,
                    prevKey = null,
                    nextKey = null
                )
            )
        }
    }

    @Test
    fun givenTheMovies_whenDeleteTheMovies_thenGetEmptyList() = runBlocking {
        // Given
        val theMovies = listOf(
            TheMoviesEntity(
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
                video = false,
                userId = "userId"

            ),
            TheMoviesEntity(
                id = 2,
                title = "The Movie 2",
                posterPath = "posterPath 2",
                backdropPath = "backdropPath 2",
                overview = "overview 2",
                releaseDate = "releaseDate 2",
                voteAverage = 2.0,
                voteCount = 2,
                popularity = 2.0,
                originalLanguage = "originalLanguage 2",
                originalTitle = "originalTitle 2",
                adult = false,
                video = false,
                userId = "userId"

            )
        )

        // When
        dao.insert(theMovies)
        dao.delete()
        dao.getAllTheMovies()
        assertThat(theMovies).doesNotContain(
            PagingSource.LoadResult.Page(
                data = theMovies,
                prevKey = null,
                nextKey = null
            )
        )
    }
}


