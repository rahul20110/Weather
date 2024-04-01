package com.example.climate.database
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "weather_table")
data class ClimateEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val date: String,
    val maxTemp: Double,
    val minTemp: Double
)
