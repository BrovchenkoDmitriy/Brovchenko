package com.example.brovchenko.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class FilmDbModel(
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val filmId:Int,
    val nameRu: String,
    val nameEn: String,
    val posterUrl: String,
    val posterUrlPreview: String,
    val year: String,
    var description: String = "",
    val countries: String,
    val genres:String,
    var chosen: Boolean = false
)