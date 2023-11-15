package com.halilkrkn.themoviesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.halilkrkn.themoviesapp.data.local.model.TheMoviesFavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TheMoviesFavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insert(favoriteMovies: TheMoviesFavoriteEntity)
    @Delete
    suspend fun delete(favoriteMovies: TheMoviesFavoriteEntity)

    @Query("SELECT * FROM movies_favorite ORDER BY id DESC")
    fun getAllFavorite(): Flow<List<TheMoviesFavoriteEntity>>

    @Query("SELECT * FROM movies_favorite WHERE title LIKE '%' || :searchQuery || '%' ORDER BY title DESC")
    fun searchFavorite(searchQuery: String): Flow<List<TheMoviesFavoriteEntity>>

}