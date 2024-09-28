package pl.madsoft.myweather.data.remote.api

import pl.madsoft.myweather.data.remote.model.CityDTO
import pl.madsoft.myweather.data.remote.model.ForecastDTO
import pl.madsoft.myweather.data.remote.model.WeatherDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AccuWeatherAPIService {

    @GET("locations/v1/cities/search")
    suspend fun searchCities(
        @Query("apikey") apiKey: String,
        @Query("q") query: String,
        @Query("language") language: String = "pl-pl"
    ): List<CityDTO>

    @GET("currentconditions/v1/{cityKey}")
    suspend fun getCurrentConditions(
        @Path("cityKey") cityKey: String,
        @Query("apikey") apiKey: String,
        @Query("language") language: String = "pl-pl",
        @Query("details") details: Boolean = true
    ): List<WeatherDTO>

    @GET("forecasts/v1/daily/5day/{cityKey}")
    suspend fun get5DayForecast(
        @Path("cityKey") cityKey: String,
        @Query("apikey") apiKey: String,
        @Query("language") language: String = "pl-pl",
        @Query("details") details: Boolean = false,
        @Query("metric") metric: Boolean = true
    ): ForecastDTO
}
