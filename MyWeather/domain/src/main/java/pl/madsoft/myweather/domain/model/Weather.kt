package pl.madsoft.myweather.domain.model

data class Weather(
    val temperature: Double,
    val cloudiness: Int,
    val precipitationProbability: Double,
    val humidity: Int
)
