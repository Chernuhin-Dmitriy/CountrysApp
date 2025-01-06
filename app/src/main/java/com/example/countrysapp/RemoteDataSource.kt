package com.example.countrysapp

import android.telecom.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface RestCountryService {
    @GET("name/{name}")
    suspend fun getCountryByName(@Path("name") cityName: String): List<Country>
}


var retrofit = Retrofit.Builder()
    .baseUrl("https://restcountries.com/v2/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var restCountrysApi = retrofit.create(RestCountryService::class.java)