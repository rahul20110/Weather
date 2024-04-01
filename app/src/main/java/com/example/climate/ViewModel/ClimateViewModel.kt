package com.example.climate.ViewModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.climate.api.ClimateService
import com.example.climate.database.AverageClimate
import com.example.climate.database.ClimateDao
import com.example.climate.database.ClimateEntity
import com.example.climate.repository.ClimateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ClimateViewModel(private val repository: ClimateRepository) : ViewModel() {
    private val _climateData = MutableLiveData<ClimateEntity>()
    val climateData: LiveData<ClimateEntity> = _climateData

    fun fetchClimate(date: String, location: String) {
        viewModelScope.launch {
            try {
                val data = repository.fetchWeather(date, location)
                _climateData.value = data
            } catch (e: Exception) {
                Log.e("ClimateViewModel", "Error fetching climate data", e)
                // Handle errors appropriately
            }
        }
    }
}