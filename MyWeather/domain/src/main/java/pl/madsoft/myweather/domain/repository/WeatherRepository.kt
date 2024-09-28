package pl.madsoft.myweather.domain.repository

import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.domain.model.Forecast
import pl.madsoft.myweather.domain.model.Weather

interface WeatherRepository {
    suspend fun searchCities(query: String): List<City>
    suspend fun getCurrentWeather(cityKey: String): Weather
    suspend fun getForecast(cityKey: String): Forecast

    suspend fun saveCity(city: City)
    suspend fun getSavedCities(): List<City>
    suspend fun getCityByKey(cityKey: String): City?
    suspend fun deleteCity(city: City)
    suspend fun updateCity(city: City)
}
