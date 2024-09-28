package pl.madsoft.myweather

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.madsoft.myweather.domain.repository.WeatherRepository
import pl.madsoft.myweather.ui.theme.MyWeatherTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var weatherRepository: WeatherRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyWeatherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        searchTest("Warszawa")
    }

    // TODO: THIS IS TEMPORARY, TO REMOVE WHEN UI WILL BE CREATED
    private fun searchTest(city: String) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val cities = weatherRepository.searchCities(city)
                val weather = weatherRepository.getCurrentWeather(cities.first().key)
                val forecast = weatherRepository.getForecast(cities.first().key)

                Log.d("City:", "$cities")
                Log.d("Weather", "$weather")
                Log.d("Forecast", "$forecast")

            } catch (e: Exception) {
                Log.e("searchTest", "Error: ${e.message}", e)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyWeatherTheme {
        Greeting("Android")
    }
}