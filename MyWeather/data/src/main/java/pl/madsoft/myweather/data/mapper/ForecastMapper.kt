package pl.madsoft.myweather.data.mapper

import pl.madsoft.myweather.data.remote.model.DailyForecastDTO
import pl.madsoft.myweather.data.remote.model.DayNightDTO
import pl.madsoft.myweather.data.remote.model.ForecastDTO
import pl.madsoft.myweather.domain.model.DailyForecast
import pl.madsoft.myweather.domain.model.Forecast
import pl.madsoft.myweather.domain.model.WeatherCondition
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun ForecastDTO.toDomainModel(): Forecast {
    val dateFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val effectiveLocalDate = OffsetDateTime.parse(this.headline.effectiveDate, dateFormatter).toLocalDate()
    val endLocalDate = OffsetDateTime.parse(this.headline.endDate, dateFormatter).toLocalDate()

    return Forecast(
        headline = this.headline.text,
        effectiveDate = effectiveLocalDate,
        endDate = endLocalDate,
        severity = this.headline.severity,
        category = this.headline.category,
        dailyForecasts = this.dailyForecasts.map { it.toDomainModel() }
    )
}

fun DailyForecastDTO.toDomainModel(): DailyForecast {
    val dateFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    val localDate = OffsetDateTime.parse(this.date, dateFormatter).toLocalDate()

    return DailyForecast(
        date = localDate,
        minTemperature = this.temperature.minimum.value,
        maxTemperature = this.temperature.maximum.value,
        dayCondition = this.day.toDomainModel(),
        nightCondition = this.night.toDomainModel(),
        sources = this.sources,
        mobileLink = this.mobileLink,
        link = this.link
    )
}

fun DayNightDTO.toDomainModel(): WeatherCondition {
    return WeatherCondition(
        icon = this.icon,
        iconPhrase = this.iconPhrase,
        hasPrecipitation = this.hasPrecipitation,
        precipitationType = this.precipitationType,
        precipitationIntensity = this.precipitationIntensity
    )
}