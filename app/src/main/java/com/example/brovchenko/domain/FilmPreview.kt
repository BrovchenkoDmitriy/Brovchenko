package com.example.brovchenko.domain

data class FilmPreview(
    val filmId: Int,
    val name: String,
    val year: String?,
    val posterUrl: String,
    val posterUrlPreview: String,
    val countries: List<String> = listOf(),
    val genres: List<String> = listOf()
)
