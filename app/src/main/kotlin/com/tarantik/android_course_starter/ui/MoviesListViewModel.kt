package com.tarantik.android_course_starter.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tarantik.android_course_starter.api.MoviesApi
import com.tarantik.android_course_starter.data.MovieDao
import com.tarantik.android_course_starter.util.Movie
import com.tarantik.android_course_starter.util.PopularMovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class MoviesListViewModel(private val movieDao: MovieDao) : ViewModel(), CoroutineScope {
    private val _state = MutableStateFlow<MoviesListUiModel>(MoviesListUiModel.Loading)
    val state = _state.asStateFlow()

    private val job = SupervisorJob()
    override val coroutineContext = Dispatchers.IO + job

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            try {
                val moviesResponse = MoviesApi.retrofitService.getPopularMovies()
                Log.d("MLVM", "Received data: $moviesResponse")

                storeMovies(moviesResponse.results)

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
                movieDao.getAll().map { list -> list.map {movie ->
                    Movie(
                        backdropPath = movie.backdropPath,
                        id = movie.id,
                        title = movie.title,
                        overview = movie.overview,
                        releaseDate = movie.releaseDate,
                        posterPath = movie.posterPath,
                    )
                } }.collect {movies ->
                    Log.d("MLVM", "Received error: $e")
                    _state.update {
                        MoviesListUiModel.Success(movies)
                    }
                }
            }
        }
    }

    private fun storeMovies(movies: List<PopularMovieEntity>) {
        launch {
            movieDao.insertAll(movies)
        }
    }
}