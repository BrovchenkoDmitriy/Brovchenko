package com.example.brovchenko.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChosenFilmDao {
    @Query("SELECT * FROM chosen_films")
    suspend fun getChosenFilmList(): List<ChosenFilmDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upgradeChosenFilm(film: ChosenFilmDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChosenFilms(films: List<ChosenFilmDbModel>)

    @Query("DELETE FROM chosen_films WHERE filmId = :filmID")
    suspend fun deleteFilmFromChosen(filmID: Int)

    @Query("SELECT*FROM films WHERE filmId=:filmId LIMIT 1")
    suspend fun getChosenFilm(filmId: Int):ChosenFilmDbModel
}