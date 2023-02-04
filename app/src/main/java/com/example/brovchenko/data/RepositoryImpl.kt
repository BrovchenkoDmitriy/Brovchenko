package com.example.brovchenko.data

import com.example.brovchenko.data.network.ServiceApi
import com.example.brovchenko.domain.FilmDetail
import com.example.brovchenko.domain.FilmPreview
import com.example.brovchenko.domain.Repository

class RepositoryImpl:Repository {
    private val mapper = Mapper()

    override suspend fun getTopPopularFilms(): List<FilmPreview> {
        //у нас запрос с пагинацией, на  одной странице 20 фильмов. Обхожу чтоб собрать весь список.
        val result = mutableListOf<FilmPreview>()
        for (page in 1..5){
            val filmDtoList = ServiceApi
                .retrofitService
                .getTopPopularFilms("TOP_100_POPULAR_FILMS",page)
            result.addAll(mapper.mapFilmPreviewDtoListToFilmPreviewList(filmDtoList))
        }
        return result
    }

    override suspend fun getChosenFilms(): List<FilmPreview> {
        TODO("Not yet implemented")
    }

    override suspend fun getFilm(filmId: Int): FilmDetail {
        val filmDetailDto = ServiceApi.retrofitService.getFilm(filmId)
        return mapper.mapFilmDetailDtoToFilmDetail(filmDetailDto)
    }
}