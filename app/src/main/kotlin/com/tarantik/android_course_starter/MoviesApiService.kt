package com.tarantik.android_course_starter

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tarantik.android_course_starter.util.PopularMovieEntity
import com.tarantik.android_course_starter.util.PopularMovieEntityResponse
import cz.tarantik.android_course_starter.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY_KEY = "api_key"

interface MoviesApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int = 1): PopularMovieEntityResponse
}

object MoviesApi {
    val retrofitService: MoviesApiService by lazy { retrofit.create(MoviesApiService::class.java) }
    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder().apply {
            addInterceptor(provideQueryInterceptor())
        }.build()
    }

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

private val retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.API_BASE_URL)
    .client(MoviesApi.okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(MoviesApi.moshi))
    .build()

private fun provideQueryInterceptor(): Interceptor = Interceptor {chain ->
    val original = chain.request()
    val originalHttpUrl = original.url

    val url = originalHttpUrl.newBuilder()
        .addQueryParameter(API_KEY_KEY, BuildConfig.API_KEY)
        .build()

    val requestBuilder = original.newBuilder().url(url)

    val request = requestBuilder.build()
    chain.proceed(request)
}