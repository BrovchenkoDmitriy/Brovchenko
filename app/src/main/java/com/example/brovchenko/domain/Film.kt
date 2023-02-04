package com.example.brovchenko.domain

data class Film (
    val filmId:Int,
    val name:String,
    val year:Int,
    val posterUrl:String,
    val posterUrlPreview:String,
    val description:String = "",
    val countries:List<String> = listOf(),
    val genres:List<String> = listOf()
        )