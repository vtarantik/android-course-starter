package com.tarantik.android_course_starter.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tarantik.android_course_starter.util.PopularMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(movies: List<PopularMovieEntity>)

    @Query("SELECT * from popular_movies ORDER BY popularity DESC")
    fun getAll(): Flow<List<PopularMovieEntity>>
}