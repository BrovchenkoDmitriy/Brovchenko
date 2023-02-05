package com.example.brovchenko.domain.useCases

import com.example.brovchenko.domain.Repository

class LoadDetailFilmDataUseCase(private val repository: Repository) {
    suspend fun loadDetailFilmData(filmId:Int) = repository.loadDetailFilmData(filmId)
}