package com.example.brovchenko.data

import com.example.brovchenko.domain.Film
import com.example.brovchenko.domain.Repository

class RepositoryImpl:Repository {
    override suspend fun getTopPopularFilms(): List<Film> {
        TODO("Not yet implemented")
    }

    override suspend fun getChosenFilms(): List<Film> {
        TODO("Not yet implemented")
    }

    override suspend fun getFilm(filmId: Int): Film {
        TODO("Not yet implemented")
    }
}