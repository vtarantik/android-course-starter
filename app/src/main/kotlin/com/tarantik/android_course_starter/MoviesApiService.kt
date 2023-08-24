package com.tarantik.android_course_starter

import cz.tarantik.android_course_starter.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.API_BASE_URL)
    .client(MoviesApi.okHttpClient)
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()

interface MoviesApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int = 1): String
}

object MoviesApi {
    val retrofitService: MoviesApiService by lazy { retrofit.create(MoviesApiService::class.java) }
    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }
}