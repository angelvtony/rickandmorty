package com.example.rickandmorty.models

data class RickMorty(
    val results: List<Result>,
)

data class Result(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)
