package com.example.brovchenko.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.brovchenko.data.RepositoryImpl
import com.example.brovchenko.domain.Film
import com.example.brovchenko.domain.useCases.GetFilmUseCase
import com.example.brovchenko.domain.useCases.LoadDetailFilmDataUseCase
import kotlinx.coroutines.launch

class FilmDetailViewModel(application: Application): AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val getFilmUseCase = GetFilmUseCase(repository)
    private val loadDetailFilmDataUseCase = LoadDetailFilmDataUseCase(repository)

    private val _filmDetail = MutableLiveData<Film>()
    val filmDetail: LiveData<Film>
        get() = _filmDetail


    fun getFilm(filmId:Int) {
        viewModelScope.launch {
            loadDetailFilmDataUseCase.loadDetailFilmData(filmId)
            _filmDetail.value = getFilmUseCase.getFilm(filmId)
        }

    }
}