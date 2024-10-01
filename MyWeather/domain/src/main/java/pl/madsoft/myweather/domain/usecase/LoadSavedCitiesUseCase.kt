package pl.madsoft.myweather.domain.usecase

import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.domain.repository.WeatherRepository

class LoadSavedCitiesUseCase (private val repository: WeatherRepository) {
    suspend operator fun invoke(): List<City> {
        return repository.getSavedCities()
    }
}