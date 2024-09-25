package pl.madsoft.myweather.data.remote.api

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class APIClient @Inject constructor() {

    private val moshi: Moshi by lazy {
        Moshi.Builder().build()
    }

    val apiService: AccuWeatherAPIService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(AccuWeatherAPIService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://dataservice.accuweather.com/"
    }
}