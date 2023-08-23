package com.tarantik.android_course_starter.util.mapper

import com.tarantik.android_course_starter.util.Movie
import com.tarantik.android_course_starter.util.PopularMovieEntity

class PopularMovieMapper : Mapper<Movie, PopularMovieEntity> {
    override fun mapToDomain(entity: PopularMovieEntity): Movie {
        return Movie(
            entity.backdropPath,
            entity.id,
            entity.title,
            entity.overview,
            entity.releaseDate,
            entity.posterPath
        )
    }

    override fun mapToEntity(domain: Movie): PopularMovieEntity = throw UnsupportedMappingException()
}