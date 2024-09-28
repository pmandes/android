package pl.madsoft.myweather.domain.model

import java.time.LocalDate

data class Forecast(
    val headline: String,
    val effectiveDate: LocalDate,
    val endDate: LocalDate,
    val severity: Int,
    val category: String,
    val dailyForecasts: List<DailyForecast>
)

data class DailyForecast(
    val date: LocalDate,
    val minTemperature: Double,
    val maxTemperature: Double,
    val dayCondition: WeatherCondition,
    val nightCondition: WeatherCondition,
    val sources: List<String>,
    val mobileLink: String,
    val link: String
)

data class WeatherCondition(
    val icon: Int,
    val iconPhrase: String,
    val hasPrecipitation: Boolean,
    val precipitationType: String?,
    val precipitationIntensity: String?
)