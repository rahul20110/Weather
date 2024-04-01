package com.example.climate.api

import com.example.climate.database.ClimateResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ClimateService {
    @GET("history.json")
    suspend fun getWeatherData(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("dt") date: String
    ): ClimateResponse
}