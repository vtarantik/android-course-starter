package com.tarantik.android_course_starter.util

import com.squareup.moshi.Json

data class PopularMovieEntityResponse(
    @Json(name = "results")
    val results: List<PopularMovieEntity>,
)