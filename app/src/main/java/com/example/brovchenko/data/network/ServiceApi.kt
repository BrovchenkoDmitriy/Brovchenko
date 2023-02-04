package com.example.brovchenko.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films/"
private const val TOKEN = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface OpenWeatherAPi {
    @Headers("accept: application/json","X-API-KEY: $TOKEN")
    @GET("top")
    suspend fun getTopPopularFilms(
        @Query("type") type: String,
        @Query("page") page: Int,
    ): TopPopularFilmDto

    @Headers("accept: application/json","X-API-KEY: $TOKEN")
    @GET("{id}")
    suspend fun getFilm(
        @Path("id") id: Int
    ):FilmDetailDto
}

object ServiceApi {
    val retrofitService: OpenWeatherAPi by lazy {
        retrofit.create(OpenWeatherAPi::class.java)
    }
}