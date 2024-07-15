package com.example.weatherapp.domain.weather

import androidx.annotation.DrawableRes
import com.example.weatherapp.R

sealed class WeatherType(
    val weatherDesc: String,
    @DrawableRes val iconRes: Int
) {
    object ClearSky : WeatherType(
        weatherDesc = "Clear sky",
        iconRes = R.drawable.sunny
    )
    object MainlyClear : WeatherType(
        weatherDesc = "Mainly clear",
        iconRes = R.drawable.sunny
    )
    object PartlyCloudy : WeatherType(
        weatherDesc = "Partly cloudy",
        iconRes = R.drawable.sunny_with_clouds
    )
    object Overcast : WeatherType(
        weatherDesc = "Overcast",
        iconRes = R.drawable.sunny_with_clouds
    )
    object Foggy : WeatherType(
        weatherDesc = "Foggy",
        iconRes = R.drawable.very_cloudy
    )
    object DepositingRimeFog : WeatherType(
        weatherDesc = "Depositing rime fog",
        iconRes = R.drawable.very_cloudy
    )
    object LightDrizzle : WeatherType(
        weatherDesc = "Light drizzle",
        iconRes = R.drawable.rainy
    )
    object ModerateDrizzle : WeatherType(
        weatherDesc = "Moderate drizzle",
        iconRes = R.drawable.rainy
    )
    object DenseDrizzle : WeatherType(
        weatherDesc = "Dense drizzle",
        iconRes = R.drawable.rainy
    )
    object LightFreezingDrizzle : WeatherType(
        weatherDesc = "Slight freezing drizzle",
        iconRes = R.drawable.cloudy_with_snow
    )
    object DenseFreezingDrizzle : WeatherType(
        weatherDesc = "Dense freezing drizzle",
        iconRes = R.drawable.cloudy_with_snow
    )
    object SlightRain : WeatherType(
        weatherDesc = "Slight rain",
        iconRes = R.drawable.rainy
    )
    object ModerateRain : WeatherType(
        weatherDesc = "Rainy",
        iconRes = R.drawable.rainy
    )
    object HeavyRain : WeatherType(
        weatherDesc = "Heavy rain",
        iconRes = R.drawable.rainy
    )
    object HeavyFreezingRain: WeatherType(
        weatherDesc = "Heavy freezing rain",
        iconRes = R.drawable.cloudy_with_snow
    )
    object SlightSnowFall: WeatherType(
        weatherDesc = "Slight snow fall",
        iconRes = R.drawable.snowy
    )
    object ModerateSnowFall: WeatherType(
        weatherDesc = "Moderate snow fall",
        iconRes = R.drawable.heavy_snow
    )
    object HeavySnowFall: WeatherType(
        weatherDesc = "Heavy snow fall",
        iconRes = R.drawable.heavy_snow
    )
    object SnowGrains: WeatherType(
        weatherDesc = "Snow grains",
        iconRes = R.drawable.heavy_snow
    )
    object SlightRainShowers: WeatherType(
        weatherDesc = "Slight rain showers",
        iconRes = R.drawable.rainy
    )
    object ModerateRainShowers: WeatherType(
        weatherDesc = "Moderate rain showers",
        iconRes = R.drawable.rainy
    )
    object ViolentRainShowers: WeatherType(
        weatherDesc = "Violent rain showers",
        iconRes = R.drawable.rainy
    )
    object SlightSnowShowers: WeatherType(
        weatherDesc = "Light snow showers",
        iconRes = R.drawable.snowy
    )
    object HeavySnowShowers: WeatherType(
        weatherDesc = "Heavy snow showers",
        iconRes = R.drawable.snowy
    )
    object ModerateThunderstorm: WeatherType(
        weatherDesc = "Moderate thunderstorm",
        iconRes = R.drawable.rain_with_thunder
    )
    object SlightHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm with slight hail",
        iconRes = R.drawable.rain_with_thunder
    )
    object HeavyHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm with heavy hail",
        iconRes = R.drawable.rain_with_thunder
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}