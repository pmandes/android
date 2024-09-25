package pl.madsoft.myweather.domain.usecase

import pl.madsoft.myweather.domain.model.Weather
import pl.madsoft.myweather.domain.repository.WeatherRepository

class GetWeatherUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(cityKey: String): Weather {
        return repository.getCurrentWeather(cityKey)
    }
}