package com.tarantik.android_course_starter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tarantik.android_course_starter.data.MovieDao

class MoviesListViewModelFactory(private val movieDao: MovieDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MoviesListViewModel::class.java)) {
            return MoviesListViewModel(movieDao) as T
        }else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}