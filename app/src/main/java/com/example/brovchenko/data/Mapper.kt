package com.example.brovchenko.data

import com.example.brovchenko.data.network.*
import com.example.brovchenko.domain.FilmDetail
import com.example.brovchenko.domain.FilmPreview

class Mapper {

    private fun mapFilmPreviewDtoToFilmPreview(filmPreviewDto: Films): FilmPreview {
        return FilmPreview(
            filmId = filmPreviewDto.filmId,
            name = filmPreviewDto.nameRu,
            year = filmPreviewDto.year,
            posterUrl = filmPreviewDto.posterUrl,
            posterUrlPreview = filmPreviewDto.posterUrlPreview,
            countries = mapListCountryToListString(filmPreviewDto.countries) ,
            genres =  mapListGenreToListString(filmPreviewDto.genres)
        )
    }

    fun mapFilmPreviewDtoListToFilmPreviewList(list: TopPopularFilmDto) =
        list.films.map {
            mapFilmPreviewDtoToFilmPreview(it)
        }




    fun mapFilmDetailDtoToFilmDetail(filmDetailDto: FilmDetailDto): FilmDetail {
        return FilmDetail(
            filmId = filmDetailDto.filmId,
            nameRu = filmDetailDto.nameRu,
            nameEn = filmDetailDto.nameEn,
            nameOriginal = filmDetailDto.nameOriginal,
            year = filmDetailDto.year,
            posterUrl = filmDetailDto.posterUrl,
            posterUrlPreview = filmDetailDto.posterUrlPreview,
            countries = mapListCountryToListString(filmDetailDto.countries) ,
            genres =  mapListGenreToListString(filmDetailDto.genres)
        )
    }

    fun mapFilmDetailDtoListToFilmDetailList(list: List<FilmDetailDto>) =
        list.map {
            mapFilmDetailDtoToFilmDetail(it)
        }



    private fun mapCountryToString(country: Country):String{
        return country.toString()
    }

    private fun mapListCountryToListString(list:List<Country>) =
        list.map {
            mapCountryToString(it)
        }

    private fun mapGenreToString(country: Genre):String{
        return country.toString()
    }

    private fun mapListGenreToListString(list:List<Genre>) =
        list.map {
            mapGenreToString(it)
        }
}