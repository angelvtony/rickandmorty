package com.example.rickandmorty.models

data class RickMorty(
    val results: List<Result>
)

data class Result(
    val image: String,
    val name: String,
)
