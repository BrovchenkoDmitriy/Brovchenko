package com.example.brovchenko.presentation

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.brovchenko.R
import com.example.brovchenko.domain.Film

class MainActivity : AppCompatActivity() {

    lateinit var testText: List<Film>
    lateinit var testText2: String
    private val viewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // все ниже для теста запросов в сеть

       // val text = findViewById<TextView>(R.id.textText)
//        viewModel.getTopPopularFilms()
//        viewModel.getFilmDetail(1405508)
//
//        viewModel.filmDetail.observe(this) {
//            testText2 = it.toString()
//            text.text = it.toString()
//            Log.d("TAGIL", testText2)
//        }
//
//        viewModel.topPopularFilms.observe(this) {
//            testText = it
//            //  text.text = it.toString()
//            Log.d("TAGIL", testText.size.toString())
//        }
    }
}