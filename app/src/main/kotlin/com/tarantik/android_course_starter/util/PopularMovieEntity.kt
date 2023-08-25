package com.tarantik.android_course_starter.util

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "popular_movies")
data class PopularMovieEntity(
    @ColumnInfo(name = "adult")
    @Json(name = "adult")
    val adult: Boolean,
    @ColumnInfo(name = "backdrop_path")
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @ColumnInfo(name = "genre_ids")
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @PrimaryKey(autoGenerate = false)
    @Json(name = "id")
    val id: Int,
    @ColumnInfo(name = "original_language")
    @Json(name = "original_language")
    val originalLanguage: String,
    @ColumnInfo(name = "overview")
    @Json(name = "overview")
    val overview: String,
    @Json(name = "popularity")
    @ColumnInfo(name = "popularity")
    val popularity: Double,
    @ColumnInfo(name = "poster_path")
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "title")
    @Json(name = "title")
    val title: String,
    @ColumnInfo(name = "video")
    @Json(name = "video")
    val video: Boolean,
    @ColumnInfo(name = "vote_average")
    @Json(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "vote_count")
    @Json(name = "vote_count")
    val voteCount: Int,
)