package pl.madsoft.myweather.data

import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import pl.madsoft.myweather.data.local.dao.CityDAO
import pl.madsoft.myweather.data.remote.api.AccuWeatherAPIService
import pl.madsoft.myweather.data.repository.WeatherRepositoryImpl
import pl.madsoft.myweather.domain.model.Forecast
import pl.madsoft.myweather.domain.model.Weather
import pl.madsoft.myweather.domain.repository.WeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.time.LocalDate

class WeatherRepositoryImplAPITest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: AccuWeatherAPIService
    private lateinit var repository: WeatherRepository
    private val apiKey = "MOCK_API_KEY"

    @MockK
    private lateinit var cityDao: CityDAO

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(AccuWeatherAPIService::class.java)

        repository = WeatherRepositoryImpl(apiService, apiKey, cityDao)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        clearAllMocks()
        unmockkAll()
    }

    @Test
    fun `searchCities returns list of cities`() = runBlocking {

        val mockResponse = getResourceAsText("mock_search_city_response.json")

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockResponse)
        )

        val cities = repository.searchCities("Warszawa")

        assertThat(cities).isNotEmpty()
        assertThat(cities.size).isEqualTo(1)

        val firstCity = cities.first()
        assertThat(firstCity.key).isEqualTo("274663")
        assertThat(firstCity.name).isEqualTo("Warszawa")
        assertThat(firstCity.region).isEqualTo("Europa")
        assertThat(firstCity.country).isEqualTo("Polska")
        assertThat(firstCity.administrativeArea).isEqualTo("Mazowieckie")
        assertThat(firstCity.latitude).isEqualTo(52.232)
        assertThat(firstCity.longitude).isEqualTo(21.007)

        val recordedRequest = mockWebServer.takeRequest()
        assertThat(recordedRequest.path).contains("/locations/v1/cities/search")
        assertThat(recordedRequest.requestUrl?.queryParameter("q")).isEqualTo("Warszawa")
        assertThat(recordedRequest.requestUrl?.queryParameter("apikey")).isEqualTo(apiKey)
    }

    @Test
    fun `getCurrentWeather returns current weather for given city`() = runBlocking {

        val mockResponse = getResourceAsText("mock_current_weather_response.json")

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockResponse)
        )

        val currentWeather = repository.getCurrentWeather("274663")

        assertThat(currentWeather).isInstanceOf(Weather::class.java)

        assertThat(currentWeather.currentTemperature).isEqualTo(19.5)
        assertThat(currentWeather.cloudiness).isEqualTo(94)
        assertThat(currentWeather.precipitationPossible).isEqualTo(false)
        assertThat(currentWeather.humidity).isEqualTo(89)
        assertThat(currentWeather.visibility).isEqualTo(14.5)
        assertThat(currentWeather.feelsLikeTemperature).isEqualTo(19.6)
        assertThat(currentWeather.windSpeed).isEqualTo(13.0)
        assertThat(currentWeather.windDirection).isEqualTo("NE")

        val recordedRequest = mockWebServer.takeRequest()
        assertThat(recordedRequest.path).contains("currentconditions/v1/274663")
        assertThat(recordedRequest.requestUrl?.queryParameter("apikey")).isEqualTo(apiKey)
    }

    @Test
    fun `getForecast returns forecast for given city`() = runBlocking {

        val mockResponse = getResourceAsText("mock_forecast_response.json")

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockResponse)
        )

        val forecast = repository.getForecast("274663")

        assertThat(forecast).isInstanceOf(Forecast::class.java)

        assertThat(forecast.headline).isEqualTo("Przewidywane przelotne opady od: dzisiejszy poranek do: jutrzejsze popołudnie")
        assertThat(forecast.effectiveDate).isEqualTo(LocalDate.of(2024, 9, 28))
        assertThat(forecast.endDate).isEqualTo(LocalDate.of(2024, 9, 29))
        assertThat(forecast.severity).isEqualTo(5)
        assertThat(forecast.category).isEqualTo("rain")

        assertThat(forecast.dailyForecasts).hasSize(5)

        val firstDailyForecast = forecast.dailyForecasts[0]
        assertThat(firstDailyForecast.date).isEqualTo(LocalDate.of(2024, 9, 28))
        assertThat(firstDailyForecast.minTemperature).isEqualTo(17.3)
        assertThat(firstDailyForecast.maxTemperature).isEqualTo(23.5)
        assertThat(firstDailyForecast.dayCondition.iconPhrase).isEqualTo("Zachmurzenie duże z przelotnymi opadami")
        assertThat(firstDailyForecast.nightCondition.iconPhrase).isEqualTo("Przelotne opady")

        val recordedRequest = mockWebServer.takeRequest()
        assertThat(recordedRequest.path).contains("forecasts/v1/daily/5day/274663")
        assertThat(recordedRequest.requestUrl?.queryParameter("apikey")).isEqualTo(apiKey)
    }

    private fun getResourceAsText(path: String): String {
        val uri = this::class.java.classLoader?.getResource(path)?.toURI()
        return File(uri).readText(Charsets.UTF_8)
    }
}