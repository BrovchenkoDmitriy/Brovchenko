package com.example.brovchenko.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FilmDao {
    @Query("SELECT * FROM films")
    suspend fun getFilmList(): List<FilmDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upgradeFilm(film: FilmDbModel)

//    @Query("DELETE FROM chosen_films WHERE filmId = :filmID")
//    suspend fun deleteFilmFromChosen(filmID: Int)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopPopularFilms(films: List<FilmDbModel>)

    @Query("DELETE FROM films")
    suspend fun clearFilmList()

    @Query("SELECT*FROM films WHERE filmId=:filmId LIMIT 1")
    suspend fun getFilm(filmId: Int):FilmDbModel
}