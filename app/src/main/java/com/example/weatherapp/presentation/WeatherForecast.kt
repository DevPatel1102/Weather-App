package com.example.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.domain.weather.WeatherData
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun WeatherForecast(
    state: WeatherState,
) {

    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->

        Column{
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(30.dp, 0.dp, 30.dp, 5.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Today",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal
                )
                val formatter = DateTimeFormatter.ofPattern("MMM''dd")
                val currentDate = LocalDate.now().format(formatter)

                Text(
                    text = currentDate,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Normal
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(40.dp))
                    .background(Color(0X60000000)) // semi-transparent black
                    .padding(15.dp)
            ) {
                LazyRow(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    content = {
                        items(data){weatherData ->
                            HourlyWeatherDisplay(
                                weatherData = weatherData,
                                modifier = Modifier
                            )
                            Spacer(modifier = Modifier.width(21.dp))
                        }
                    }
                )
            }
        }
    }
}