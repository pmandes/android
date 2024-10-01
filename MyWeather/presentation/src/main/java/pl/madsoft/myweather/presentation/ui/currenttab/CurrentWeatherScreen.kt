package pl.madsoft.myweather.presentation.ui.currenttab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.madsoft.myweather.presentation.R
import pl.madsoft.myweather.presentation.viewmodel.MainViewState

@Composable
fun CurrentWeatherScreen(state: MainViewState) {
    when (state) {
        is MainViewState.ShowCurrentWeather -> {

            val weather = state.weather
            val city = state.city
            val forecast = state.forecast

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)

            ) {

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    CityInfoCard(city = city, currentTemperature = weather.currentTemperature)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    WeatherInfoCard(weather = weather)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    ForecastCard(forecast = forecast)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        else -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = stringResource(R.string.no_weather_data))
            }
        }
    }
}

