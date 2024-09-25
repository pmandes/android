package pl.madsoft.myweather.data.repository

import pl.madsoft.myweather.data.di.APIKey
import pl.madsoft.myweather.data.mapper.toDomainModel
import pl.madsoft.myweather.data.remote.api.AccuWeatherAPIService
import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.domain.model.Forecast
import pl.madsoft.myweather.domain.model.Weather
import pl.madsoft.myweather.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: AccuWeatherAPIService,
    @APIKey private val apiKey: String
) : WeatherRepository {

    override suspend fun searchCities(query: String): List<City> {
        val response = apiService.searchCities(apiKey, query)
        return response.map { city ->
            city.toDomainModel()
        }
    }

    override suspend fun getCurrentWeather(cityKey: String): Weather {
        TODO("Not yet implemented")
    }

    override suspend fun getForecast(cityKey: String): Forecast {
        TODO("Not yet implemented")
    }
}