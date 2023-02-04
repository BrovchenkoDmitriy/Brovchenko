package com.example.brovchenko.domain


data class FilmDetail(
    val filmId: Int = 0,
    val nameRu: String? = null,
    val nameEn: String? = null,
    val nameOriginal: String? = null,
    val posterUrl: String? = null,
    val posterUrlPreview: String? = null,
    val year: Int? = 0,
    val description: String? = null,
    val countries: List<String>? = listOf(),
    val genres: List<String>? = listOf()
)