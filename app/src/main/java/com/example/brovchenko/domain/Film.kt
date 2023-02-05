package com.example.brovchenko.domain

data class Film(
    val id:Int = 0,
    val filmId: Int,
    val nameRu: String,
    val nameEn: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val year: String,
    var description: String,
    val countries: List<String>,
    val genres: List<String>,
    var chosen: Boolean
)