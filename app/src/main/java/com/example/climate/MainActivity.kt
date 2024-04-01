package com.example.climate



import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.climate.api.RetrofitInstance
import com.example.climate.repository.ClimateRepository
import com.example.climate.ViewModel.ClimateViewModel
import com.example.climate.ViewModel.ClimateViewModelFactory
import com.example.climate.database.ClimateDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ClimateViewModel

    private lateinit var dateInput: EditText
    private lateinit var locationInput: EditText
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = ClimateDatabase.getDatabase(this)
        val repository = ClimateRepository(database.weatherDao(), RetrofitInstance.api)
        val factory = ClimateViewModelFactory(repository)

        dateInput = findViewById(R.id.dateInput)
        locationInput = findViewById(R.id.locationInput)
        searchButton = findViewById(R.id.searchButton)




        viewModel = ViewModelProvider(this, factory).get(ClimateViewModel::class.java)

        searchButton.setOnClickListener {
            val inputDate = dateInput.text.toString()
            val inputLocation = locationInput.text.toString()



            viewModel.fetchClimate(inputDate, inputLocation) // Fetch climate data when the search button is clicked


        }



        viewModel.climateData.observe(this) { climate ->
            // Update the UI when the climate data changes
            findViewById<TextView>(R.id.tvWeatherInfo).text =
                "Max Temp: ${climate.maxTemp}, Min Temp: ${climate.minTemp}"
        }

    }
}