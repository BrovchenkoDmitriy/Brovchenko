package com.example.brovchenko.data.network

import com.squareup.moshi.Json

data class FilmDetailDto(
    @Json(name = "kinopoiskId") val filmId: Int = 0,
    val nameRu: String? = "null",
    val nameEn: String? = "null",
    val nameOriginal: String? = "null",
    val posterUrl: String? = "null",
    val posterUrlPreview: String? = "null",
    val year: Int? = 0,
    val description: String? = "null",
    val countries: List<Country> = listOf(),
    val genres: List<Genre> = listOf()
)

