package com.example.brovchenko.presentation.filmsRecyclerView

import androidx.recyclerview.widget.DiffUtil
import com.example.brovchenko.domain.Film


class FilmsDiffCallBack:DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldWeatherItem: Film, newWeatherItem: Film): Boolean {
        return oldWeatherItem.filmId == newWeatherItem.filmId
    }

    override fun areContentsTheSame(oldWeatherItem: Film, newWeatherItem: Film): Boolean {
        return oldWeatherItem == newWeatherItem
    }
}