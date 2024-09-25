package pl.madsoft.myweather.domain.usecase

import pl.madsoft.myweather.domain.model.Forecast
import pl.madsoft.myweather.domain.repository.WeatherRepository

class GetForecastUseCase(private val repository: WeatherRepository) {
    suspend operator fun invoke(cityKey: String): Forecast {
        return repository.getForecast(cityKey)
    }
}