package com.example.weatherapp.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import android.location.Geocoder
import android.location.Address
import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import java.util.Locale
import com.airbnb.lottie.compose.*

@AndroidEntryPoint
class MainActivity : ComponentActivity()  {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher : ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ){
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ))

        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Box (modifier = Modifier.fillMaxSize()){
                    Weather_Page(state = viewModel.state)
                    if(viewModel.state.isLoading){
//                        CircularProgressIndicator(
//                            modifier = Modifier.align(Alignment.Center)
//                        )
                        LottieLoadingAnimation()
                    }
                    viewModel.state.error?.let { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

val poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)



@Composable
fun Weather_Page(
    state : WeatherState
) {

    var city: String
    val context = LocalContext.current

    state.weatherInfo?.currentWeatherData?.let { data ->
        Scaffold(
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    when (data.weatherType.weatherDesc.trim()) {
                        "Clear sky", "Mainly clear" -> {
                            Image(
                                painter = painterResource(id = R.drawable.sunny_weather),
                                contentDescription = "Sunny Weather",
                                contentScale = ContentScale.Crop,
                            )
                        }
                        "Partly cloudy", "Overcast" -> {
                            Image(
                                painter = painterResource(id = R.drawable.sunny_with_clouds_background),
                                contentDescription = "Cloudy Weather",
                                contentScale = ContentScale.Crop,
                            )
                        }
                        else -> {
                            Image(
                                painter = painterResource(id = R.drawable.rainy_weather),
                                contentDescription = "Rainy Weather",
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                alpha = 0.2f
                            }
                            .background(Color.Black)
                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "${data.temperatureCelsius}°",
                                color = Color.Black,
                                fontSize = 50.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight.SemiBold,
                            )
                            city = getCityName(context, state.latitude, state.longitude).toString()
                            Text(
                                text = city,
                                color = Color.Black,
                                fontSize = 30.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = data.weatherType.weatherDesc,
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontFamily = poppins,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp, 20.dp, 15.dp, 30.dp),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Add top content here if needed

                        // Bottom temperature box
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .absoluteOffset(0.dp, 0.dp)
                        ) {
                            WeatherForecast(state = state)
                        }
                    }

                }
            }
        )
    }
}

fun getCityName(context: Context, latitude: Double, longitude: Double): String? {
    val geocoder = Geocoder(context, Locale.getDefault())
    val addresses: List<Address>?
    var cityName: String? = null

    try {
        addresses = geocoder.getFromLocation(latitude, longitude, 1)
        if (addresses != null && addresses.isNotEmpty()) {
            val address: Address = addresses[0]
            cityName = address.locality
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return cityName
}

@Composable
fun LottieLoadingAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(250.dp).fillMaxSize(),
        alignment = Alignment.Center
    )}

}




