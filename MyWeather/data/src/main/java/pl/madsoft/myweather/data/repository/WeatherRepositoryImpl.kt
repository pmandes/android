package pl.madsoft.myweather.data.repository

import pl.madsoft.myweather.data.di.APIKey
import pl.madsoft.myweather.data.local.dao.CityDAO
import pl.madsoft.myweather.data.mapper.toDomainModel
import pl.madsoft.myweather.data.mapper.toEntity
import pl.madsoft.myweather.data.remote.api.AccuWeatherAPIService
import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.domain.model.Forecast
import pl.madsoft.myweather.domain.model.Weather
import pl.madsoft.myweather.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: AccuWeatherAPIService,
    @APIKey private val apiKey: String,
    private val cityDao: CityDAO
) : WeatherRepository {

    override suspend fun searchCities(query: String): List<City> {
        val response = apiService.searchCities(apiKey, query)
        return response.map { city ->
            city.toDomainModel()
        }
    }

    override suspend fun getCurrentWeather(cityKey: String): Weather {
        val response = apiService.getCurrentConditions(cityKey, apiKey)
        return response.first().toDomainModel()
    }

    override suspend fun getForecast(cityKey: String): Forecast {
        val response = apiService.get5DayForecast(cityKey, apiKey)
        return response.toDomainModel()
    }

    override suspend fun saveCity(city: City) {
        cityDao.insertCity(city.toEntity())
    }

    override suspend fun getSavedCities(): List<City> {
        return cityDao.getAllCities().map { it.toDomainModel() }
    }

    override suspend fun getCityByKey(cityKey: String): City? {
        return cityDao.getCityByKey(cityKey)?.toDomainModel()
    }

    override suspend fun deleteCity(city: City) {
        cityDao.deleteCity(city.toEntity())
    }

    override suspend fun updateCity(city: City) {
        cityDao.updateCity(city.toEntity())
    }
}