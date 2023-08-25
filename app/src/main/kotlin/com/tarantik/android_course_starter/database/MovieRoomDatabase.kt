package com.tarantik.android_course_starter.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tarantik.android_course_starter.data.MovieDao
import com.tarantik.android_course_starter.util.PopularMovieEntity

@Database(entities = [PopularMovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieRoomDatabase : RoomDatabase() {
    abstract fun popularMoviesDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieRoomDatabase? = null

        fun getDatabase(context: Context): MovieRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieRoomDatabase::class.java,
                    "movie_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        const val Name = "MovieDB"
    }
}