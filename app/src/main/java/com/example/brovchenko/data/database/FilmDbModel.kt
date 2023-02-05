package com.example.brovchenko.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class FilmDbModel(
    @PrimaryKey(autoGenerate = false)
    val filmId:Int,
    val nameRu: String,
    val nameEn: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val year: String,
    val description: String = "",
    val countries: String,
    val genres:String,
    val chosen: Boolean = false
)