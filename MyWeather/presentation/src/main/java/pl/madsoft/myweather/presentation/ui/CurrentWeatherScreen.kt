package pl.madsoft.myweather.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import pl.madsoft.myweather.presentation.viewmodel.MainViewState

@Composable
fun CurrentWeatherScreen(state: MainViewState) {
    when (state) {
        is MainViewState.ShowCurrentWeather -> {

        }
        else -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Current Weather Screen")
            }
        }
    }
}
