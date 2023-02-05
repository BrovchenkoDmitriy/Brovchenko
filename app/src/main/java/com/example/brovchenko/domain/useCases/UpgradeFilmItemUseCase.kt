package com.example.brovchenko.domain.useCases

import com.example.brovchenko.domain.Film
import com.example.brovchenko.domain.Repository

class UpgradeFilmItemUseCase(private val repository: Repository) {
    suspend fun upgradeFilmItem(film: Film){
        repository.upgradeFilm(film)
    }
}