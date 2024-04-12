package com.atvss.marvellist.api

import com.atvss.marvellist.data.Hero
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    // marvel is endpoint
    @GET("marvel")
    // returning object type Call which is list(object where data from server are going to be stored).
    // Hero (call) object will give us ability to create asynchronous requests to the api
    fun getHeroes(): Call<List<Hero>>
}