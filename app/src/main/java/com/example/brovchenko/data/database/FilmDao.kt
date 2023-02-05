package com.example.brovchenko.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FilmDao {
    @Query("SELECT * FROM films")
    suspend fun getFilmList(): List<FilmDbModel>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upgradeChosenStatus(film: FilmDbModel)

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopPopularFilms(films: List<FilmDbModel>)

    @Query("DELETE FROM films")
    suspend fun clearFilmList()

    @Query("SELECT*FROM films WHERE filmId=:filmId LIMIT 1")
    suspend fun getShopItem(filmId: Int):FilmDbModel
}