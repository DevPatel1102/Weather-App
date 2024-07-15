package com.example.weatherapp.presentation

import com.example.weatherapp.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)
