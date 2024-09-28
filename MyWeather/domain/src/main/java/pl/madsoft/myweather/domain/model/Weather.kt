package pl.madsoft.myweather.domain.model

data class Weather(
    val currentTemperature: Double,
    val cloudiness: Int,
    val precipitationPossible: Boolean,
    val humidity: Int,
    val visibility: Double,
    val feelsLikeTemperature: Double,
    val windSpeed: Double,
    val windDirection: String
)