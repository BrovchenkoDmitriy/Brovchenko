package com.example.brovchenko.domain.useCases

import com.example.brovchenko.domain.Repository

class GetTopPopularFilmsUseCase(private val repository: Repository) {
    suspend fun getTopPopularFilms() = repository.getTopPopularFilms()
}