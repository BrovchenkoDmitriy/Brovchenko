package com.example.brovchenko.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.brovchenko.data.RepositoryImpl
import com.example.brovchenko.domain.Film
import com.example.brovchenko.domain.useCases.GetTopPopularFilmsUseCase
import kotlinx.coroutines.launch

class FilmListViewModel(application: Application): AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)
    private val getTopPopularFilmsUseCase = GetTopPopularFilmsUseCase(repository)
    //private val upgradePositionItemUseCase = UpgradePositionItemUseCase(repository)

    private val _topPopularFilms = MutableLiveData<List<Film>>()
    val topPopularFilms: LiveData<List<Film>>
        get() = _topPopularFilms

    fun getTopPopularFilms(){
        viewModelScope.launch{
        _topPopularFilms.value =  getTopPopularFilmsUseCase.getTopPopularFilms()
        }
    }
}