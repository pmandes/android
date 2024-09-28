package pl.madsoft.myweather.data.mapper

import pl.madsoft.myweather.data.remote.model.WeatherDTO
import pl.madsoft.myweather.domain.model.Weather

fun WeatherDTO.toDomainModel(): Weather {
    return Weather(
        currentTemperature = this.temperature.metric.value,
        cloudiness = this.cloudCover,
        precipitationPossible = this.hasPrecipitation,
        humidity = this.relativeHumidity,
        visibility = this.visibility.metric.value,
        feelsLikeTemperature = this.realFeelTemperature.metric.value,
        windSpeed = this.wind.speed.metric.value,
        windDirection = this.wind.direction.localized
    )
}