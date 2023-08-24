package com.tarantik.android_course_starter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tarantik.android_course_starter.ui.MoviesListUiModel
import com.tarantik.android_course_starter.util.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import java.net.UnknownHostException

class MoviesListViewModel : ViewModel() {
    private val _state = MutableStateFlow<MoviesListUiModel>(MoviesListUiModel.Loading)
    val state = _state.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            try {
                val moviesResponse = MoviesApi.retrofitService.getPopularMovies()
                Log.d("MLVM", "Received data: $moviesResponse")

                _state.update {
                    MoviesListUiModel.Success(
                        moviesResponse.results.map { movie ->
                            Movie(
                                backdropPath = movie.backdropPath,
                                id = movie.id,
                                title = movie.title,
                                overview = movie.overview,
                                releaseDate = movie.releaseDate,
                                posterPath = movie.posterPath,
                            )
                        }
                    )
                }
            } catch (e: IOException) {
                Log.d("MLVM", "Received error: $e")
                _state.update {
                    MoviesListUiModel.Error(e)
                }
            }
        }
    }
}