package pl.madsoft.myweather.data

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import pl.madsoft.myweather.data.remote.api.AccuWeatherAPIService
import pl.madsoft.myweather.data.repository.WeatherRepositoryImpl
import pl.madsoft.myweather.domain.repository.WeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class WeatherRepositoryImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: AccuWeatherAPIService
    private lateinit var repository: WeatherRepository
    private val apiKey = "MOCK_API_KEY"

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(AccuWeatherAPIService::class.java)

        repository = WeatherRepositoryImpl(apiService, apiKey)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `searchCities returns list of cities`() = runBlocking {

        val mockResponse = """
            [
                {
                    "Key": "332946",
                    "LocalizedName": "Warszawa",
                    "Region": {
                        "LocalizedName": "Europa"
                    },
                    "Country": {
                        "LocalizedName": "Polska"
                    },
                    "AdministrativeArea": {
                        "LocalizedName": "Mazowieckie"
                    },
                    "GeoPosition": {
                        "Latitude": 52.232,
                        "Longitude": 21.007
                    }
                }
            ]
        """.trimIndent()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockResponse)
        )

        val cities = repository.searchCities("Warszawa")

        assertThat(cities).isNotEmpty()
        assertThat(cities.size).isEqualTo(1)

        val firstCity = cities.first()
        assertThat(firstCity.key).isEqualTo("332946")
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
}