package com.example.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.domain.weather.WeatherData
import java.time.format.DateTimeFormatter

@Composable
fun HourlyWeatherDisplay(
    weatherData: WeatherData,
    modifier: Modifier =  Modifier
){
    val formattedTime = remember(weatherData) {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = formattedTime,
            color = Color.White,
            fontSize = 15.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(5.dp))
        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier.size(32.dp),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "${weatherData.temperatureCelsius}°",
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = poppins,
            fontWeight = FontWeight.Normal
        )
    }
}