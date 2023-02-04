package com.example.brovchenko.data.network


data class TopPopularFilmDto (
    val pagesCount: Int = 0,
    val films: List<Films>
)

data class Films (
    val filmId: Int = 0,
    val nameRu: String = "",
    val nameEn: String? = "",
    val year: String? = "",
    val countries: List<Country> = listOf(),
    val genres: List<Genre> = listOf(),
    val posterUrl: String = "",
    val posterUrlPreview: String = "",
)