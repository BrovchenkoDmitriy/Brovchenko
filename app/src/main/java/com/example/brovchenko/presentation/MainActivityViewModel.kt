package com.example.brovchenko.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brovchenko.data.RepositoryImpl
import com.example.brovchenko.domain.Film
import com.example.brovchenko.domain.useCases.GetFilmUseCase
import com.example.brovchenko.domain.useCases.GetTopPopularFilmsUseCase
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    // ВьюМодель создана для тестов запросов в сеть

    private val repository = RepositoryImpl()
    private val getTopPopularFilmsUseCase = GetTopPopularFilmsUseCase(repository)
    private val getFilmUseCase = GetFilmUseCase(repository)


    private val _topPopularFilms = MutableLiveData<List<Film>>()
    val topPopularFilms: LiveData<List<Film>>
        get() = _topPopularFilms

    private val _filmDetail = MutableLiveData<Film>()
    val filmDetail: LiveData<Film>
        get() = _filmDetail


    fun getTopPopularFilms() {
        viewModelScope.launch {
            _topPopularFilms.value = getTopPopularFilmsUseCase.getTopPopularFilms()
           // Log.d("TAGIL",_topPopularFilms.value.toString())
        }
    }

    fun getFilmDetail(filmId:Int){
        viewModelScope.launch {
            _filmDetail.value = getFilmUseCase.getFilm(filmId)
         //   Log.d("TAGIL", _filmDetail.value.toString())
        }
    }
}