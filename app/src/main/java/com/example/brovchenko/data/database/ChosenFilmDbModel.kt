package com.example.brovchenko.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chosen_films")
data class ChosenFilmDbModel(
    @PrimaryKey(autoGenerate = false)
    val filmId: Int,
    val nameRu: String,
    val nameEn: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val year: String,
    var description: String? = "",
    val countries: String,
    val genres: String,
    var chosen: Boolean = false
)