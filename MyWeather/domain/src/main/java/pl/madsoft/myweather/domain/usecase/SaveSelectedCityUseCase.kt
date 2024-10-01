package pl.madsoft.myweather.domain.usecase

import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.domain.repository.WeatherRepository

class SaveSelectedCityUseCase (private val repository: WeatherRepository) {
    suspend operator fun invoke(city: City) {
        return repository.saveCity(city)
    }
}
