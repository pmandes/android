package pl.madsoft.myweather.domain.usecase

import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.domain.repository.WeatherRepository

interface ILoadSavedCitiesUseCase {
    suspend operator fun invoke(): List<City>
}

class LoadSavedCitiesUseCase (private val repository: WeatherRepository) : ILoadSavedCitiesUseCase {
    override suspend operator fun invoke(): List<City> {
        return repository.getSavedCities()
    }
}