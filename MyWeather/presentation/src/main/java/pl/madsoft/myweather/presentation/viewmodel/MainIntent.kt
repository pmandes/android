package pl.madsoft.myweather.presentation.viewmodel

sealed class MainIntent {
    data class SearchCity(val query: String) : MainIntent()
}