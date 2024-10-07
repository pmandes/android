package pl.madsoft.myweather.domain.usecase

import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.domain.repository.WeatherRepository

interface ISaveSelectedCityUseCase {
    suspend operator fun invoke(city: City)
}

class SaveSelectedCityUseCase (private val repository: WeatherRepository) : ISaveSelectedCityUseCase {
    override suspend operator fun invoke(city: City) {
        return repository.saveCity(city)
    }
}
