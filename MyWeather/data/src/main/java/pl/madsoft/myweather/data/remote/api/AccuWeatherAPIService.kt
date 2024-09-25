package pl.madsoft.myweather.data.remote.api

import pl.madsoft.myweather.data.remote.model.CityDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface AccuWeatherAPIService {

    @GET("locations/v1/cities/search")
    suspend fun searchCities(
        @Query("apikey") apiKey: String,
        @Query("q") query: String,
        @Query("language") language: String = "pl-pl"
    ): List<CityDTO>
}
