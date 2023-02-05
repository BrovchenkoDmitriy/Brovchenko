package com.example.brovchenko.domain.useCases

import androidx.lifecycle.LiveData
import com.example.brovchenko.domain.Film
import com.example.brovchenko.domain.Repository

class GetChosenFilmsUseCase(private val repository: Repository) {
    suspend fun getChosenFilms():List<Film> = repository.getChosenFilms()
}