package com.example.brovchenko.domain

interface Repository {
    suspend fun getTopPopularFilms():List<FilmPreview>
    suspend fun getChosenFilms():List<FilmPreview>
    suspend fun getFilm(filmId:Int):FilmDetail
}
