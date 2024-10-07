package pl.madsoft.myweather.presentation.viewmodel

import pl.madsoft.myweather.domain.model.City

sealed class MainIntent {
    data class SearchCity(val query: String) : MainIntent()
    data class SelectCity(val city: City) : MainIntent()
    object ShowCurrentWeather : MainIntent()
    object ShowSearchHistory : MainIntent()
}