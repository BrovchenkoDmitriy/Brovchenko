package com.example.brovchenko.presentation.filmList

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brovchenko.R
import com.example.brovchenko.databinding.FragmentFilmListBinding
import com.example.brovchenko.presentation.FilmDetailFragment
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmListBinding.inflate(layoutInflater, container, false)
        val orientation = resources.configuration.orientation
        if (orientation== Configuration.ORIENTATION_LANDSCAPE){
            requireActivity().supportFragmentManager.popBackStack()
        }else{
            requireActivity().supportFragmentManager.popBackStack()
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.topPopularFilms.observe(viewLifecycleOwner){
           if(it.isEmpty()){
               binding.tvStatus.text = "Ошибка загрузки"
           }
        }


        viewModel.isChosenList.observe(viewLifecycleOwner){
            if(it){
                binding.topPopularButton.isEnabled = true
                binding.chosenButton.isEnabled = false
            }else {
                binding.topPopularButton.isEnabled = false
                binding.chosenButton.isEnabled = true
            }
        }


        viewModel.currentFilms.observe(viewLifecycleOwner) {
            filmsAdapter.submitList(it)
        }
        binding.topPopularButton.setOnClickListener {
            viewModel.getTopPopularFilms()
            binding.topPopularButton.isEnabled = false
            binding.chosenButton.isEnabled = true
        }
        binding.chosenButton.setOnClickListener {
            viewModel.getChosenFilms()
            binding.topPopularButton.isEnabled = true
            binding.chosenButton.isEnabled = false
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
            val orientation = resources.configuration.orientation
            if (orientation== Configuration.ORIENTATION_PORTRAIT){
                requireActivity().supportFragmentManager.popBackStack()
                launchFilmDetailFragment(FilmDetailFragment.newInstanceEditItem(it.filmId))
            } else{
                requireActivity().supportFragmentManager.popBackStack()
                launchFilmDetailFragmentLandScape(FilmDetailFragment.newInstanceEditItem(it.filmId))
            }
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
    private fun launchFilmDetailFragmentLandScape(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.second_container, fragment)
            .addToBackStack(null).commit()
    }


}