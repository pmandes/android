package pl.madsoft.myweather.domain.usecase

import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.domain.repository.WeatherRepository

class SearchCityUseCase (private val repository: WeatherRepository) {
    suspend operator fun invoke(query: String): List<City> {
        return repository.searchCities(query)
    }
}