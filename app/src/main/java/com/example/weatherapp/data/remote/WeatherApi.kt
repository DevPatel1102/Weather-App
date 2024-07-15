package com.example.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast?hourly=temperature_2m,weather_code")
    suspend fun getWeatherData(
        @Query("latitude") lat:Double,
        @Query("longitude") long:Double
    ) : WeatherDto
}