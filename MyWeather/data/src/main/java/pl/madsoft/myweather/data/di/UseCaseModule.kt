package pl.madsoft.myweather.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.madsoft.myweather.data.repository.WeatherRepositoryImpl
import pl.madsoft.myweather.domain.usecase.GetForecastUseCase
import pl.madsoft.myweather.domain.usecase.GetWeatherUseCase
import pl.madsoft.myweather.domain.usecase.SearchCityUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSearchCityUseCase(
        repository: WeatherRepositoryImpl
    ): SearchCityUseCase {
        return SearchCityUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetWeatherUseCase(
        repository: WeatherRepositoryImpl
    ): GetWeatherUseCase {
        return GetWeatherUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetForecastUseCase(
        repository: WeatherRepositoryImpl
    ): GetForecastUseCase {
        return GetForecastUseCase(repository)
    }
}