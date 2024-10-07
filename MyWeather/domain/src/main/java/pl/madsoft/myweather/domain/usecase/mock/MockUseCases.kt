package pl.madsoft.myweather.domain.usecase.mock

import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.domain.model.DailyForecast
import pl.madsoft.myweather.domain.model.Forecast
import pl.madsoft.myweather.domain.model.Weather
import pl.madsoft.myweather.domain.model.WeatherCondition
import pl.madsoft.myweather.domain.usecase.IGetForecastUseCase
import pl.madsoft.myweather.domain.usecase.IGetWeatherUseCase
import pl.madsoft.myweather.domain.usecase.ISearchCityUseCase
import java.time.LocalDate

class MockSearchCityUseCase : ISearchCityUseCase {
    override suspend fun invoke(query: String): List<City> {
        return MockData.getMockCities()
    }
}

class MockGetWeatherUseCase : IGetWeatherUseCase {
    override suspend fun invoke(cityKey: String): Weather {
        return MockData.getMockWeather()
    }
}

class MockGetForecastUseCase : IGetForecastUseCase {
    override suspend fun invoke(cityKey: String): Forecast {
        return MockData.getMockForecast()
    }
}

object MockData {

    fun getMockCities() : List<City> {
        return listOf(
            City(
                key = "12345",
                name = "Warszawa",
                region = "Europa",
                country = "Polska",
                administrativeArea = "Mazowieckie",
                latitude = 52.232,
                longitude = 21.007
            ),
            City(
                key = "23456",
                name = "Kraków",
                region = "Europa",
                country = "Polska",
                administrativeArea = "Małopolskie",
                latitude = 52.232,
                longitude = 21.007
            )
        )
    }

    fun getMockWeather() : Weather {
        return Weather(
            currentTemperature = 32.9,
            cloudiness = 35,
            precipitationPossible = false,
            humidity = 50,
            visibility = 16.1,
            feelsLikeTemperature = 14.0,
            windSpeed = 3.7,
            windDirection = "N"
        )
    }

    fun getMockForecast(): Forecast {
        return Forecast(
            headline = "Przewidywane przelotne opady od: dzisiejszy poranek do: jutrzejsze popołudnie",
            effectiveDate = LocalDate.parse("2024-09-28"),
            endDate = LocalDate.parse("2024-09-29"),
            severity = 5,
            category = "rain",
            dailyForecasts = listOf(
                DailyForecast(
                    date = LocalDate.parse("2024-09-28"),
                    minTemperature = 17.3,
                    maxTemperature = 23.5,
                    dayCondition = WeatherCondition(
                        icon = 13,
                        iconPhrase = "Zachmurzenie duże z przelotnymi opadami",
                        hasPrecipitation = true,
                        precipitationType = "Rain",
                        precipitationIntensity = "Light"
                    ),
                    nightCondition = WeatherCondition(
                        icon = 12,
                        iconPhrase = "Przelotne opady",
                        hasPrecipitation = true,
                        precipitationType = "Rain",
                        precipitationIntensity = "Light"
                    ),
                    sources = listOf("AccuWeather"),
                    mobileLink = "http://www.accuweather.com/pl/us/warsaw-in/46580/daily-weather-forecast/332946?day=1&unit=c",
                    link = "http://www.accuweather.com/pl/us/warsaw-in/46580/daily-weather-forecast/332946?day=1&unit=c"
                ),
                DailyForecast(
                    date = LocalDate.parse("2024-09-29"),
                    minTemperature = 16.0,
                    maxTemperature = 23.3,
                    dayCondition = WeatherCondition(
                        icon = 12,
                        iconPhrase = "Przelotne opady",
                        hasPrecipitation = true,
                        precipitationType = "Rain",
                        precipitationIntensity = "Light"
                    ),
                    nightCondition = WeatherCondition(
                        icon = 8,
                        iconPhrase = "Ponuro",
                        hasPrecipitation = false,
                        precipitationType = null,
                        precipitationIntensity = null
                    ),
                    sources = listOf("AccuWeather"),
                    mobileLink = "http://www.accuweather.com/pl/us/warsaw-in/46580/daily-weather-forecast/332946?day=2&unit=c",
                    link = "http://www.accuweather.com/pl/us/warsaw-in/46580/daily-weather-forecast/332946?day=2&unit=c"
                )
            )
        )
    }
}