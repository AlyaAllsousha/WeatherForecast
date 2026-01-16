package com.example.myapplication.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    private const val url = "https://api.weatherapi.com";
    private fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
     val weatherApi : WeatherApi = getInstance().create(WeatherApi::class.java)
}