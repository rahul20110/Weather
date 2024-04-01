package com.example.climate.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.climate.repository.ClimateRepository

class ClimateViewModelFactory(private val repository: ClimateRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClimateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClimateViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}