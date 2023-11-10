package com.halilkrkn.themoviesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TheMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insert(theMovies: List<TheMoviesEntity>)

    @Delete
    suspend fun delete(theMovies: TheMoviesEntity)

    @Query("SELECT * FROM movies ORDER BY id DESC")
    fun getAllArticle(): Flow<List<TheMoviesEntity>>

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :searchQuery || '%' ORDER BY title DESC")
    fun searchSavedName(searchQuery: String): Flow<List<TheMoviesEntity>>
}