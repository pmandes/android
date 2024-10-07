package pl.madsoft.myweather.domain.usecase

import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.domain.repository.WeatherRepository

interface ILoadCityUseCase {
    suspend operator fun invoke(key: String): City?
}

class LoadCityUseCase (private val repository: WeatherRepository) : ILoadCityUseCase {
    override suspend operator fun invoke(key: String): City? {
        return repository.getCityByKey(key)
    }
}