package com.halilkrkn.themoviesapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TheMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(theMovies: List<TheMoviesEntity>)

    @Query ("DELETE FROM movies")
    suspend fun delete()

    @Query("SELECT * FROM movies")
    fun getAllTheMovies(): PagingSource<Int, TheMoviesEntity>

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :searchQuery || '%' ORDER BY title DESC")
    fun searchSavedName(searchQuery: String): Flow<List<TheMoviesEntity>>
}