package com.example.brovchenko.domain.useCases

import com.example.brovchenko.domain.Repository

class GetFilmUseCase (private val repository: Repository){
    suspend fun getFilm(FilmId:Int) = repository.getFilm(FilmId)
}