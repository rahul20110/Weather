package com.example.climate.repository

import android.util.Log
import com.example.climate.api.ClimateService
import com.example.climate.database.AverageClimate
import com.example.climate.database.ClimateDao
import com.example.climate.database.ClimateEntity
import com.example.climate.database.ClimateResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class ClimateRepository(private val climateDao: ClimateDao, private val climateService: ClimateService) {

    suspend fun fetchWeather(date: String, location: String): ClimateEntity {
        val response = climateService.getWeatherData("8a4a490d2e724507a2f151316240104", location, date)
        val weather = response.forecast.forecastday.firstOrNull()?.let { forecastDay ->
            ClimateEntity(
                id = 0, // You might want to change the logic for ID
                date = forecastDay.date,
                maxTemp = forecastDay.day.maxtemp_c,
                minTemp = forecastDay.day.mintemp_c
            )
        }
        weather?.let {
            climateDao.insertWeather(it)
        }
        return weather ?: throw Exception("No weather data available")
    }
}