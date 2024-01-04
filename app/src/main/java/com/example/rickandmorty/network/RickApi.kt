package com.example.rickandmorty.network

import com.example.rickandmorty.models.RickMorty
import retrofit2.Call
import retrofit2.http.GET

interface RickApi  {
    @GET("character")
    fun getDetails() : Call<RickMorty>
}