package pl.madsoft.myweather.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.madsoft.myweather.data.repository.WeatherRepositoryImpl
import pl.madsoft.myweather.domain.usecase.GetForecastUseCase
import pl.madsoft.myweather.domain.usecase.GetWeatherUseCase
import pl.madsoft.myweather.domain.usecase.LoadCityUseCase
import pl.madsoft.myweather.domain.usecase.LoadSavedCitiesUseCase
import pl.madsoft.myweather.domain.usecase.SaveSelectedCityUseCase
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

    @Provides
    @Singleton
    fun provideLoadSavedCitiesUseCase(
        repository: WeatherRepositoryImpl
    ): LoadSavedCitiesUseCase {
        return LoadSavedCitiesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSaveSelectedCityUseCase(
        repository: WeatherRepositoryImpl
    ): SaveSelectedCityUseCase {
        return SaveSelectedCityUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoadCityUseCase(
        repository: WeatherRepositoryImpl
    ): LoadCityUseCase {
        return LoadCityUseCase(repository)
    }
}