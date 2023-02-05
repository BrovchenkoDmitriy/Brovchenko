package com.example.brovchenko.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FilmDao {
    @Query("SELECT * FROM films")
    fun getFilmList(): LiveData<List<FilmDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upgradeChosenStatus(weatherWeek: List<FilmDbModel>)

    @Query("DELETE FROM films")
    suspend fun clearFilmList()
}