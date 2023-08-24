package com.tarantik.android_course_starter.ui

import com.tarantik.android_course_starter.util.Movie

sealed interface MoviesListUiModel {
    object Loading: MoviesListUiModel
    data class Success(val movies: List<Movie>): MoviesListUiModel
    data class Error(val error: Throwable): MoviesListUiModel
}