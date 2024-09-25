package pl.madsoft.myweather.domain.model

data class Forecast(
    val city: City,
    val dailyForecasts: List<DailyForecast> = emptyList()
)

data class DailyForecast(
    val date: String,
    val minTemperature: Double,
    val maxTemperature: Double,
    val precipitationProbability: Double,
    val description: String
)
