package pl.madsoft.myweather.presentation.viewmodel

import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.domain.model.Weather

sealed class MainViewState {
    object Idle : MainViewState()
    object Loading : MainViewState()
    data class ShowCurrentWeather(val weather: Weather) : MainViewState()
    data class ShowSearchHistory(val history: List<City>) : MainViewState()
    data class Error(val message: String) : MainViewState()
}
