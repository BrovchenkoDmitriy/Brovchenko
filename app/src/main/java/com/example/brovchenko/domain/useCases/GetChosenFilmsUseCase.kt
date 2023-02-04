package com.example.brovchenko.domain.useCases

import com.example.brovchenko.domain.Repository

class GetChosenFilmsUseCase(private val repository: Repository) {
    suspend fun getChosenFilms() = repository.getChosenFilms()
}