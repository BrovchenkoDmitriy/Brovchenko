package com.example.brovchenko.data

import com.example.brovchenko.data.database.FilmDbModel
import com.example.brovchenko.data.network.*
import com.example.brovchenko.domain.Film
import java.lang.StringBuilder

class Mapper {


                         // FilmPreviewDto to FilmDbModel

    fun mapFilmPreviewDtoToFilmDbModel(filmPreviewDto: FilmPreviewDto): FilmDbModel {
        return FilmDbModel(
            filmId = filmPreviewDto.filmId,
            nameRu = filmPreviewDto.nameRu ?: "",
            nameEn = filmPreviewDto.nameEn ?: "",
            posterUrl = filmPreviewDto.posterUrl ?: "",
            posterUrlPreview = filmPreviewDto.posterUrlPreview ?: "",
            year = filmPreviewDto.year ?: "",
            countries = mapListToString(mapListCountryToListString(filmPreviewDto.countries)),
            genres = mapListToString(mapListGenreToListString(filmPreviewDto.genres))
        )
    }

    fun mapFilmPreviewDtoListToFilmDbModelList(list: List<FilmPreviewDto>) =
        list.map {
            mapFilmPreviewDtoToFilmDbModel(it)
        }

                        // FilmDetailDto to FilmDbModel

    fun mapFilmDetailDtoToFilmDbModel(filmDetailDto: FilmDetailDto): FilmDbModel {
        return FilmDbModel(
            filmId = filmDetailDto.filmId,
            nameRu = filmDetailDto.nameRu ?: "",
            nameEn = filmDetailDto.nameEn ?: "",
            posterUrl = filmDetailDto.posterUrl ?: "",
            posterUrlPreview = filmDetailDto.posterUrlPreview ?: "",
            year = filmDetailDto.year.toString(),
            description = filmDetailDto.description ?: "",
            countries = mapListToString(mapListCountryToListString(filmDetailDto.countries)),
            genres = mapListToString(mapListGenreToListString(filmDetailDto.genres))
        )
    }

    fun mapFilmDetailDtoListToFilmDbModelList(list: List<FilmDetailDto>) =
        list.map {
            mapFilmDetailDtoToFilmDbModel(it)
        }

                                    // FilmDbModel to Film

    fun mapFilmDbModelToFilm(filmDbModel: FilmDbModel): Film {
        val a = filmDbModel.countries
        return Film(
            filmId = filmDbModel.filmId,
            nameRu = filmDbModel.nameRu,
            nameEn = filmDbModel.nameEn,
            posterUrl = filmDbModel.posterUrl,
            posterUrlPreview = filmDbModel.posterUrlPreview,
            year = filmDbModel.year,
            description = filmDbModel.description,
            countries = mapStringToList(filmDbModel.countries),
            genres = mapStringToList(filmDbModel.genres),
            chosen = filmDbModel.chosen
        )
    }

    fun mapFilmDbModelListToFilmList(list: List<FilmDbModel>) =
        list.map {
            mapFilmDbModelToFilm(it)
        }


    /////////////////////////////////////////////////////////////////


    private fun mapCountryToString(country: Country): String {
        return country.toString()
    }

    private fun mapListCountryToListString(list: List<Country>) =
        list.map {
            mapCountryToString(it)
        }

    private fun mapGenreToString(country: Genre): String {
        return country.toString()
    }

    private fun mapListGenreToListString(list: List<Genre>) =
        list.map {
            mapGenreToString(it)
        }


    private fun mapListToString(list:List<String>):String{
        val stringBuilder = StringBuilder()
        for (element in list){
            stringBuilder.append(element).append(" ")
        }
        return stringBuilder.toString()
    }

    private fun mapStringToList (string:String):List<String>{
        return string.split(" ")
    }
}