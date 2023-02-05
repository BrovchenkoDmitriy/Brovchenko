package com.example.brovchenko.domain

data class Film(
    val filmId: Int,
    val nameRu: String,
    val nameEn: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val year: String,
    val description: String,
    val countries: List<String>,
    val genres: List<String>,
    val chosen: Boolean
)