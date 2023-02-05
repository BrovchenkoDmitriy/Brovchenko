package com.example.brovchenko.data

import com.example.brovchenko.data.database.FilmDbModel
import com.example.brovchenko.data.network.ServiceApi
import com.example.brovchenko.domain.Repository
import com.example.brovchenko.domain.Film

class RepositoryImpl:Repository {
    private val mapper = Mapper()

    override suspend fun getTopPopularFilms(): List<Film> {
        //у нас запрос с пагинацией, на  одной странице 20 фильмов. Обхожу чтоб собрать весь список.
        //val result = mutableListOf<FilmPreview>()
        val temp = mutableListOf<FilmDbModel>()
        for (page in 1..5) {
            val filmDtoList = ServiceApi
                .retrofitService
                .getTopPopularFilms("TOP_100_POPULAR_FILMS", page)
            //result.addAll(mapper.mapFilmPreviewDtoListToFilmPreviewList(filmDtoList))
            temp.addAll(mapper.mapFilmPreviewDtoListToFilmDbModelList(filmDtoList.filmDto))
        }
        // mapper.mapFilmPreviewDtoListToFilmDbModelList(result)
        return mapper.mapFilmDbModelListToFilmList(temp)
    }

    override suspend fun getChosenFilms(): List<Film> {
        TODO("Not yet implemented")
    }

    override suspend fun getFilm(filmId: Int): Film {
        val filmDetailDto = ServiceApi.retrofitService.getFilm(filmId)
        val filmDbModel = mapper.mapFilmDetailDtoToFilmDbModel(filmDetailDto)
        return mapper.mapFilmDbModelToFilm(filmDbModel)
    }
}