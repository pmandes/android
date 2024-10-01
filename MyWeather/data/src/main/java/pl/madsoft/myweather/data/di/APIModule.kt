package pl.madsoft.myweather.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.madsoft.myweather.data.BuildConfig
import pl.madsoft.myweather.data.remote.api.APIClient
import pl.madsoft.myweather.data.remote.api.AccuWeatherAPIService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {

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
}