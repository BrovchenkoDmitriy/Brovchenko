package com.example.brovchenko.data.network

import com.squareup.moshi.Json


data class TopPopularFilmDto (
    val pagesCount: Int = 0,
    @Json(name = "films")val filmDto: List<FilmPreviewDto>
)

data class FilmPreviewDto (
    val filmId: Int = 0,
    val nameRu: String?= "",
    val nameEn: String? = "",
    val year: String? = "",
    val countries: List<Country> = listOf(),
    val genres: List<Genre> = listOf(),
    val posterUrl: String? = "",
    val posterUrlPreview: String? = "",
)