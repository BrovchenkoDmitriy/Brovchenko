package com.example.brovchenko.data.network

import com.squareup.moshi.Json

data class FilmDetailDto(
    @Json(name = "kinopoiskId") val filmId: Int,
    val nameRu: String? = "null",
    val nameEn: String? = "null",
    val description: String? = "null",
)

