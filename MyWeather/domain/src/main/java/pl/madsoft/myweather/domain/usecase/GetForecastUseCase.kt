package pl.madsoft.myweather.domain.usecase

import pl.madsoft.myweather.domain.model.Forecast
import pl.madsoft.myweather.domain.repository.WeatherRepository

interface IGetForecastUseCase {
    suspend operator fun invoke(cityKey: String): Forecast
}

class GetForecastUseCase(private val repository: WeatherRepository) : IGetForecastUseCase{
    override suspend operator fun invoke(cityKey: String): Forecast {
        return repository.getForecast(cityKey)
    }
}