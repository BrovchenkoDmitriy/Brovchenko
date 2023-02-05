package com.example.brovchenko.data

import android.app.Application
import android.util.Log
import com.example.brovchenko.data.database.AppDataBase
import com.example.brovchenko.data.database.FilmDao
import com.example.brovchenko.data.database.FilmDbModel
import com.example.brovchenko.data.network.ServiceApi
import com.example.brovchenko.domain.Repository
import com.example.brovchenko.domain.Film
import java.lang.RuntimeException

class RepositoryImpl(application: Application) : Repository {

    private val mapper = Mapper()
    private val filmDao = AppDataBase.getInstance(application).filmDao()

    override suspend fun loadData() {
        val temp = mutableListOf<FilmDbModel>()
        try {
            for (page in 1..5) {
                val filmDtoList = ServiceApi
                    .retrofitService
                    .getTopPopularFilms("TOP_100_POPULAR_FILMS", page)
                temp.addAll(mapper.mapFilmPreviewDtoListToFilmDbModelList(filmDtoList.filmDto))
            }
        } catch (e: Exception) {
            throw RuntimeException("Error load TopPopularFilms: $e")
        }
        filmDao.clearFilmList()
        filmDao.insertTopPopularFilms(temp)
    }

    override suspend fun getTopPopularFilms(): List<Film> {
        val listFilmDbModel = filmDao.getFilmList()
        return mapper.mapFilmDbModelListToFilmList(listFilmDbModel)
    }


    override suspend fun getChosenFilms(): List<Film> {
        TODO("Not yet implemented")
    }

    override suspend fun upgradeFilm(film: Film) {
        val filmDbModel = mapper.mapFilmToFilmDbModel(film)
        filmDao.upgradeChosenStatus(filmDbModel)
    }

    override suspend fun loadDetailFilmData(filmId: Int) {
        val filmDbModel = filmDao.getShopItem(filmId)
        try {
            val filmDetailDto = ServiceApi.retrofitService.getFilm(filmDbModel.filmId)
             filmDbModel.description = filmDetailDto.description.toString()
            filmDao.upgradeChosenStatus(filmDbModel)
        } catch (e: java.lang.Exception) {
        }
    }

    override suspend fun getFilm(filmId: Int): Film {
        val filmDbModel = filmDao.getShopItem(filmId)
        return mapper.mapFilmDbModelToFilm(filmDbModel)
    }
}
