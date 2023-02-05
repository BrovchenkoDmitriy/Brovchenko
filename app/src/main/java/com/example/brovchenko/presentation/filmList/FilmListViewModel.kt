package com.example.brovchenko.presentation.filmList

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.brovchenko.data.RepositoryImpl
import com.example.brovchenko.domain.Film
import com.example.brovchenko.domain.useCases.GetTopPopularFilmsUseCase
import com.example.brovchenko.domain.useCases.LoadDataUseCase
import com.example.brovchenko.domain.useCases.UpgradeFilmItemUseCase
import kotlinx.coroutines.launch

class FilmListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val getTopPopularFilmsUseCase = GetTopPopularFilmsUseCase(repository)
    private val upgradeFilmItemUseCase = UpgradeFilmItemUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    private val _topPopularFilms = MutableLiveData<List<Film>>()
    val topPopularFilms: LiveData<List<Film>>
        get() = _topPopularFilms

    private val _chosenFilms = MutableLiveData<List<Film>>()
    val chosenFilms: LiveData<List<Film>>
        get() = _chosenFilms

    init{
        getTopPopularFilms()
    }

    private fun getTopPopularFilms() {
        viewModelScope.launch {
            loadDataUseCase()
            _topPopularFilms.value = getTopPopularFilmsUseCase.getTopPopularFilms()
        }
    }

    fun upgradeFilmItem(film: Film) {
        viewModelScope.launch {
            val newFilm = film.copy(chosen = !film.chosen)
            upgradeFilmItemUseCase.upgradeFilmItem(newFilm)
            _topPopularFilms.value = getTopPopularFilmsUseCase.getTopPopularFilms()
        }

    }
}