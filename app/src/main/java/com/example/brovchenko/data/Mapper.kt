package com.example.brovchenko.data

import com.example.brovchenko.data.database.FilmDbModel
import com.example.brovchenko.data.network.Country
import com.example.brovchenko.data.network.FilmPreviewDto
import com.example.brovchenko.data.network.Genre
import com.example.brovchenko.domain.Film

class Mapper {


    // FilmPreviewDto to FilmDbModel

    fun mapFilmPreviewDtoToFilmDbModel(filmPreviewDto: FilmPreviewDto): FilmDbModel {
        return FilmDbModel(
            id = 0,
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


    // FilmDbModel to Film

    fun mapFilmDbModelToFilm(filmDbModel: FilmDbModel): Film {
        return Film(
            id = filmDbModel.id,
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


    //  mapFilmToFilmDbModel

    fun mapFilmToFilmDbModel(film: Film): FilmDbModel {
        return FilmDbModel(
            id = film.id,
            filmId = film.filmId,
            nameRu = film.nameRu,
            nameEn = film.nameEn,
            posterUrl = film.posterUrl,
            posterUrlPreview = film.posterUrlPreview,
            year = film.year,
            description = film.description,
            countries = mapListToString(film.countries),
            genres = mapListToString(film.genres),
            chosen = film.chosen
        )
    }

    fun mapFilmListToFilmDbModelList(list: List<Film>) =
        list.map {
            mapFilmToFilmDbModel(it)
        }

    /////////////////////////////////////////////////////////////////


    private fun mapCountryToString(country: Country): String {
        return country.country.toString()
    }

    private fun mapListCountryToListString(list: List<Country>) =
        list.map {
            mapCountryToString(it)
        }

    private fun mapGenreToString(genre: Genre): String {
        return genre.genre.toString()
    }

    private fun mapListGenreToListString(list: List<Genre>) =
        list.map {
            mapGenreToString(it)
        }


    private fun mapListToString(list: List<String>): String {
        val stringBuilder = StringBuilder()
        for (element in list) {
            stringBuilder.append(element).append(",")
        }
        val string = stringBuilder.toString()
        return string.substring(0, string.length - 1)
    }

    private fun mapStringToList(string: String): List<String> {
        return string.split(",")
    }
}