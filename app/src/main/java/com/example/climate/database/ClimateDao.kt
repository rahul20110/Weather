package com.example.climate.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface ClimateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(vararg weatherEntity: ClimateEntity)

    @Query("SELECT * FROM weather_table WHERE date = :date")
    suspend fun getWeatherByDate(date: String): ClimateEntity

    // Use this query for future dates to compute averages
    @Query("SELECT AVG(maxTemp) as averageMaxTemp, AVG(minTemp) as averageMinTemp FROM weather_table")
    suspend fun getAverageWeather(): AverageClimate


    @Query("SELECT * FROM weather_table")
    suspend fun getAllWeather(): List<ClimateEntity> // New method

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(weatherEntities: List<ClimateEntity>) // New method
}
