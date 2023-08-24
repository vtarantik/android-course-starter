package com.tarantik.android_course_starter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tarantik.android_course_starter.util.Movie
import kotlinx.coroutines.launch

class MoviesListViewModel : ViewModel() {
    fun loadMovies() {
        viewModelScope.launch {
            val moviesResponse = MoviesApi.retrofitService.getPopularMovies()
            Log.d("MLVM", "Received data: $moviesResponse")
        }
    }
}