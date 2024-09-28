package pl.madsoft.myweather.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.madsoft.myweather.data.BuildConfig
import pl.madsoft.myweather.data.local.dao.CityDAO
import pl.madsoft.myweather.data.local.database.MyWeatherAppDatabase
import pl.madsoft.myweather.data.remote.api.APIClient
import pl.madsoft.myweather.data.remote.api.AccuWeatherAPIService
import pl.madsoft.myweather.data.repository.WeatherRepositoryImpl
import pl.madsoft.myweather.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    @APIKey
    fun provideApiKey(): String {
        return BuildConfig.ACCUWEATHER_API_KEY
    }

    @Provides
    @Singleton
    fun provideApiClient(): APIClient {
        return APIClient()
    }

    @Provides
    @Singleton
    fun provideAccuWeatherApiService(apiClient: APIClient): AccuWeatherAPIService {
        return apiClient.apiService
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): MyWeatherAppDatabase {
        return Room.databaseBuilder(
            context,
            MyWeatherAppDatabase::class.java,
            "my_weather_app_db"
        ).build()
    }

    @Provides
    fun provideCityDao(appDatabase: MyWeatherAppDatabase): CityDAO {
        return appDatabase.cityDao()
    }

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