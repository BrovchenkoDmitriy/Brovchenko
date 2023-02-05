package com.example.brovchenko.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.brovchenko.R
import com.example.brovchenko.databinding.FragmentFilmDetailBinding
import kotlin.properties.Delegates


class FilmDetailFragment : Fragment() {

    var filmId by Delegates.notNull<Int>()

    private var _binding: FragmentFilmDetailBinding? = null
    private val binding: FragmentFilmDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentFilmDetailBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(this)[FilmDetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParam()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFilm(filmId)
        viewModel.filmDetail.observe(viewLifecycleOwner) {

            val genres = "Жанры: " + getStringWithPunctuation(it.genres)
            val countries = "Страны: " + getStringWithPunctuation(it.countries)
            with(binding) {
                tvFilmCountries.text = countries
                tvFilmDescription.text = it.description
                tvFilmGenries.text = genres
                tvFilmName.text = it.nameRu
                bindImage(ivFilmPoster, it.posterUrl)
            }
        }
    }

    private fun parseParam() {
        val args = requireArguments()
        if (!args.containsKey(FILM_ID)) {
            throw java.lang.RuntimeException("Param shopItemId is absent")
        }
        filmId = args.getInt(FILM_ID, 0)
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

    private fun getStringWithPunctuation(list: List<String>): String {
        val stringBuilder = StringBuilder()
        for (element in list) {
            stringBuilder.append(element).append(", ")
        }
        val result = stringBuilder.toString()
        return result.substring(0, result.length - 2)
    }


    companion object {
        private const val FILM_ID = "FilmId"
        fun newInstanceEditItem(filmId: Int): Fragment {
            return FilmDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(FILM_ID, filmId)
                }
            }
        }
    }
}