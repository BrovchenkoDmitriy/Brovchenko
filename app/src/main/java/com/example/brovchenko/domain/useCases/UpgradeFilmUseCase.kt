package com.example.brovchenko.domain.useCases

import com.example.brovchenko.domain.Film
import com.example.brovchenko.domain.Repository

class UpgradeFilmUseCase(private val repository: Repository) {
    suspend fun upgradeFilm(film: Film){
        repository.upgradeFilm(film)
    }
}