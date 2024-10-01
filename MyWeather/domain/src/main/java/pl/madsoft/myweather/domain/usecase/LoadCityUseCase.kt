package pl.madsoft.myweather.domain.usecase

import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.domain.repository.WeatherRepository

class LoadCityUseCase (private val repository: WeatherRepository) {
    suspend operator fun invoke(key: String): City? {
        return repository.getCityByKey(key)
    }
}