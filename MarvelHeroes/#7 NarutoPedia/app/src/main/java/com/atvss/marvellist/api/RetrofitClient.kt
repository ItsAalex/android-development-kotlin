package com.atvss.marvellist.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// retrofit is library for send and get data from api
// this is like a factory for creating Retrofit instances
object RetrofitClient {
    private const val BASE_URL = "https://simplifiedcoding.net/demos/"

    // prepearing for singleton pattern
    var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                // working with json data
                .addConverterFactory(GsonConverterFactory.create())
                // building retrofit object
                .build()
        }
        return retrofit!!
    }
}