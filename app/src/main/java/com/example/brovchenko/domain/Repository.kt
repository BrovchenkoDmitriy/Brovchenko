package com.example.brovchenko.domain

interface Repository {
    suspend fun getTopPopularFilms():List<Film>
    suspend fun getChosenFilms():List<Film>
    suspend fun getFilm(filmId:Int): Film
}
