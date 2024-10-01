package pl.madsoft.myweather.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.madsoft.myweather.data.local.dao.CityDAO
import pl.madsoft.myweather.data.remote.api.AccuWeatherAPIService
import pl.madsoft.myweather.data.repository.WeatherRepositoryImpl
import pl.madsoft.myweather.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        apiService: AccuWeatherAPIService,
        @APIKey apiKey: String,
        cityDAO: CityDAO
    ): WeatherRepository {
        return WeatherRepositoryImpl(apiService, apiKey, cityDAO)
    }
}