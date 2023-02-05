package com.example.brovchenko.data

import android.app.Application
import android.util.Log
import com.example.brovchenko.data.database.AppDataBase
import com.example.brovchenko.data.database.FilmDbModel
import com.example.brovchenko.data.network.ServiceApi
import com.example.brovchenko.domain.Repository
import com.example.brovchenko.domain.Film
import java.lang.RuntimeException

class RepositoryImpl(application: Application) : Repository {

    private val mapper = Mapper()
    private val filmDao = AppDataBase.getInstance(application).filmDao()
    private val chosenFilmDao = AppDataBase.getInstance(application).chosenFilmDao()

    override suspend fun loadData() {

        val list = mutableListOf<FilmDbModel>()
        val chosenList = chosenFilmDao.getChosenFilmList()

        try {
            for (page in 1..5) {
                val filmDtoList = ServiceApi
                    .retrofitService
                    .getTopPopularFilms("TOP_100_POPULAR_FILMS", page)
                list.addAll(mapper.mapFilmPreviewDtoListToFilmDbModelList(filmDtoList.filmDto))
            }
            for (film in list){
                for (chosenFilm in chosenList){
                    if(film.filmId==chosenFilm.filmId) {
                        film.chosen = chosenFilm.chosen
                        film.description = chosenFilm.description
                    }
                }
            }
            filmDao.clearFilmList()
            filmDao.insertTopPopularFilms(list)
        } catch (e: Exception) {
            //throw RuntimeException("Error load TopPopularFilms: $e")
            filmDao.clearFilmList()
        }
    }

    override suspend fun getTopPopularFilms(): List<Film> {
        val listFilmDbModel = filmDao.getFilmList()
        return mapper.mapFilmDbModelListToFilmList(listFilmDbModel)
    }


    override suspend fun getChosenFilms(): List<Film> {
        val listFilmDbModel = chosenFilmDao.getChosenFilmList()
        return mapper.mapChosenFilmDbModelListToFilmList(listFilmDbModel)
    }

    override suspend fun upgradeFilm(film: Film) {
        filmDao.upgradeFilm(mapper.mapFilmToFilmDbModel(film))
        if (film.chosen){
            chosenFilmDao.upgradeChosenFilm(mapper.mapFilmToChosenFilmDbModel(film))
        } else (chosenFilmDao.deleteFilmFromChosen(film.filmId))

    }

    override suspend fun loadDetailFilmData(filmId: Int) {

        try {
            val filmDetailDto = ServiceApi.retrofitService.getFilm(filmId)
            val description = filmDetailDto.description.toString()



            val chosenFilmDbModel = chosenFilmDao.getChosenFilm(filmId)
            val filmDbModel = filmDao.getFilm(filmId)
            chosenFilmDbModel.description=description
            filmDbModel.description = description

            chosenFilmDao.upgradeChosenFilm(chosenFilmDbModel)
            filmDao.upgradeFilm(filmDbModel)
        } catch (e: Exception) {
        }

    }

    override suspend fun getFilm(filmId: Int): Film {
        val filmDbModel = filmDao.getFilm(filmId)
        val chosenFilms = chosenFilmDao.getChosenFilmList()
        for(chosenFilm in chosenFilms) {
            if (chosenFilm.filmId == filmId) {
                return mapper.mapChosenFilmDbModelToFilm(chosenFilm)
            }
                val films = filmDao.getFilmList()
                for (film in films) {
                    if (film.filmId == filmId) {
                        return mapper.mapFilmDbModelToFilm(film)
                    }
                }


        }
        throw RuntimeException("Not exist film")
    }
}
