package pl.madsoft.myweather.domain.usecase

import pl.madsoft.myweather.domain.model.Weather
import pl.madsoft.myweather.domain.repository.WeatherRepository

interface IGetWeatherUseCase {
    suspend operator fun invoke(cityKey: String): Weather
}

class GetWeatherUseCase(private val repository: WeatherRepository) : IGetWeatherUseCase {
    override suspend operator fun invoke(cityKey: String): Weather {
        return repository.getCurrentWeather(cityKey)
    }
}