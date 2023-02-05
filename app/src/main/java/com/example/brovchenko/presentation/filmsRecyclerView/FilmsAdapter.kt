package com.example.brovchenko.presentation.filmsRecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.brovchenko.R
import com.example.brovchenko.databinding.FilmItemBinding
import com.example.brovchenko.domain.Film

class FilmsAdapter :
    ListAdapter<Film, FilmsViewHolder>(FilmsDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        Log.d("TAG", "onCreateViewHolder")
        val binding = FilmItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FilmsViewHolder(binding)
    }

    private fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            imgView.load(imgUri) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: FilmsViewHolder, position: Int) {
        val film = getItem(position)
        val poster = film.posterUrlPreview
        with(viewHolder.binding){
            tvFilmItemName.text = film.nameRu
            tvFilmItemYear.text = film.year
            bindImage(ivFilmItemPoster,poster)
        }

    }
}