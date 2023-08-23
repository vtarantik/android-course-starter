package com.tarantik.android_course_starter.util

data class MovieDetailEntity(
    val adult: Boolean,
    val id: Int,
    val backDropPath: String,
    val belongsToCollection: CollectionEntity?,
    val budget: Int,
    val genres: List<GenreEntity>,
    val homepage: String,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompanyEntity>,
    val productionCountries: List<ProductionCountryEntity>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguageEntity>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

data class CollectionEntity(
    val id: Int,
    val name: String,
    val overview: String?,
    val posterPath: String,
    val backdropPath: String,
)

data class GenreEntity(
    val id: Int,
    val name: String,
)

data class ProductionCompanyEntity(
    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String,
)

data class ProductionCountryEntity(
    val iso_3166_1: String,
    val name: String,
)

data class SpokenLanguageEntity(
    val iso_639_1: String,
    val name: String,
)