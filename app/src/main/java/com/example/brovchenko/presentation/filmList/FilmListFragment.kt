package com.example.brovchenko.presentation.filmList

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brovchenko.R
import com.example.brovchenko.databinding.FragmentFilmListBinding
import com.example.brovchenko.presentation.FilmDetailFragment
import com.example.brovchenko.presentation.MainActivity
import com.example.brovchenko.presentation.filmList.filmsRecyclerView.FilmsAdapter


class FilmListFragment : Fragment() {

    private var _binding: FragmentFilmListBinding? = null
    private val binding: FragmentFilmListBinding
        get() = _binding ?: throw RuntimeException("FragmentFilmListBinding is null")

    private lateinit var filmsAdapter: FilmsAdapter

    private val viewModel by lazy {
        ViewModelProvider(
            this
        )[FilmListViewModel::class.java]
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        //viewModel.getTopPopularFilms()
        requireActivity().requestedOrientation


        viewModel.topPopularFilms.observe(viewLifecycleOwner) {
            filmsAdapter.submitList(it)
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvFilmList) {
            layoutManager = LinearLayoutManager(activity)
            filmsAdapter = FilmsAdapter()
            adapter = filmsAdapter
        }
        setupClickListener()
        setupLongClickListener()
    }

    private fun setupClickListener() {
        filmsAdapter.onPositionItemClickListener = {
            launchFilmDetailFragment(FilmDetailFragment.newInstanceEditItem(it.filmId))

        }
    }

    private fun setupLongClickListener() {
        filmsAdapter.onPositionItemLongClickListener = {
            viewModel.upgradeFilmItem(it)
        }
    }

    private fun launchFilmDetailFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(null).commit()
    }


}