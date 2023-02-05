package com.example.brovchenko.presentation.filmList

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.brovchenko.data.RepositoryImpl
import com.example.brovchenko.domain.Film
import com.example.brovchenko.domain.useCases.*
import kotlinx.coroutines.launch


class FilmListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val getTopPopularFilmsUseCase = GetTopPopularFilmsUseCase(repository)
    private val upgradeFilmItemUseCase = UpgradeFilmUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)
    private val getChosenFilmsUseCase = GetChosenFilmsUseCase(repository)
    private val getFilmUseCase = GetFilmUseCase(repository)


    private val _isChosenList = MutableLiveData<Boolean>()
    val isChosenList: LiveData<Boolean>
        get() = _isChosenList

    private val _topPopularFilms = MutableLiveData<List<Film>>()
    val topPopularFilms: LiveData<List<Film>>
        get() = _topPopularFilms

    private val _chosenFilms = MutableLiveData<List<Film>>()
    val chosenFilms: LiveData<List<Film>>
        get() = _chosenFilms

    private val _currentFilms = MutableLiveData<List<Film>>()
    val currentFilms: LiveData<List<Film>>
        get() = _currentFilms

    init {
        loadData()
        Log.d("TAGIL","init{} FilmListViewModel")
    }

    private fun loadData() {
        viewModelScope.launch {
                loadDataUseCase()
            _topPopularFilms.value = getTopPopularFilmsUseCase.getTopPopularFilms()
            _currentFilms.value = topPopularFilms.value
            _isChosenList.value = false

        }
    }

    fun getTopPopularFilms() {
        Log.d("TAGIL","getTopPopularFilms FilmListViewModel")
        viewModelScope.launch {
            _topPopularFilms.value = getTopPopularFilmsUseCase.getTopPopularFilms()
            _currentFilms.value = topPopularFilms.value
            _isChosenList.value = false
        }
    }

    fun getChosenFilms() {
        Log.d("TAGIL","getChosenFilms FilmListViewModel")
        viewModelScope.launch {
            _chosenFilms.value = getChosenFilmsUseCase.getChosenFilms()
            _currentFilms.value = chosenFilms.value
            _isChosenList.value = true
        }
    }

    fun upgradeFilmItem(film: Film) {
        Log.d("TAGIL","upgradeFilmItem FilmListViewModel")
        viewModelScope.launch {

            Log.d("TAGIL", film.toString())
            val newFilm = film.copy(chosen = !film.chosen)
            Log.d("TAGIL", newFilm.toString())
            upgradeFilmItemUseCase.upgradeFilm(newFilm)

            if(isChosenList.value == true){
                _currentFilms.value = getChosenFilmsUseCase.getChosenFilms()
            } else{
                _currentFilms.value = getTopPopularFilmsUseCase.getTopPopularFilms()
            }

        }

    }
}