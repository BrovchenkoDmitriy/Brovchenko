package com.example.brovchenko.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brovchenko.databinding.FragmentFilmListBinding
import com.example.brovchenko.presentation.filmsRecyclerView.FilmsAdapter


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
    ): View? {
        _binding = FragmentFilmListBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.getTopPopularFilms()
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
    }

    companion object {

    }
}