package com.tarantik.android_course_starter

import android.app.Application
import com.tarantik.android_course_starter.database.MovieRoomDatabase

class MoviesApplication: Application() {
    val database: MovieRoomDatabase by lazy { MovieRoomDatabase.getDatabase(this) }
}