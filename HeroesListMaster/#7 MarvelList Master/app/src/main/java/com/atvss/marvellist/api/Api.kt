package com.atvss.marvellist.api

import com.atvss.marvellist.data.Hero
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("character")
    fun getCharacters(): Call<List<Hero>>
}