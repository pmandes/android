package pl.madsoft.myweather.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import pl.madsoft.myweather.data.local.dao.CityDAO
import pl.madsoft.myweather.data.local.database.MyWeatherAppDatabase
import pl.madsoft.myweather.data.remote.api.AccuWeatherAPIService
import pl.madsoft.myweather.data.repository.WeatherRepositoryImpl
import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.domain.repository.WeatherRepository

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [26], manifest = Config.NONE)
class WeatherRepositoryImplDBTest {

    private lateinit var appDatabase: MyWeatherAppDatabase
    private lateinit var cityDao: CityDAO
    private lateinit var repository: WeatherRepository

    @MockK
    private lateinit var apiService: AccuWeatherAPIService

    private val apiKey = "MOCK_API_KEY"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MyWeatherAppDatabase::class.java
        ).allowMainThreadQueries().build()

        cityDao = appDatabase.cityDao()

        repository = WeatherRepositoryImpl(apiService, apiKey, cityDao)
    }

    @After
    fun tearDown() {
        appDatabase.close()
        clearAllMocks()
        unmockkAll()
    }

    @Test
    fun `saveCity saves a city and getSavedCities retrieves it`() = runBlocking {

        val city = City(
            key = "274663",
            name = "Warszawa",
            region = "Europa",
            country = "Polska",
            administrativeArea = "Mazowieckie",
            latitude = 52.2297,
            longitude = 21.0122
        )

        repository.saveCity(city)
        val savedCities = repository.getSavedCities()

        assertThat(savedCities).isNotEmpty()
        assertThat(savedCities.size).isEqualTo(1)
        val savedCity = savedCities.first()
        assertThat(savedCity).isEqualTo(city)
    }

    @Test
    fun `getCityByKey retrieves the correct city`() = runBlocking {

        val city = City(
            key = "274663",
            name = "Warszawa",
            region = "Europa",
            country = "Polska",
            administrativeArea = "Mazowieckie",
            latitude = 52.2297,
            longitude = 21.0122
        )

        repository.saveCity(city)

        val retrievedCity = repository.getCityByKey("274663")

        assertThat(retrievedCity).isNotNull()
        assertThat(retrievedCity).isEqualTo(city)
    }

    @Test
    fun `deleteCity removes the city from the database`() = runBlocking {

        val city = City(
            key = "274663",
            name = "Warszawa",
            region = "Europa",
            country = "Polska",
            administrativeArea = "Mazowieckie",
            latitude = 52.2297,
            longitude = 21.0122
        )

        repository.saveCity(city)

        var savedCities = repository.getSavedCities()
        assertThat(savedCities).isNotEmpty()

        repository.deleteCity(city)

        savedCities = repository.getSavedCities()
        assertThat(savedCities).isEmpty()
    }

    @Test
    fun `updateCity updates the city's information`() = runBlocking {

        val city = City(
            key = "274663",
            name = "Warszawa",
            region = "Europa",
            country = "Polska",
            administrativeArea = "Mazowieckie",
            latitude = 52.2297,
            longitude = 21.0122
        )

        repository.saveCity(city)

        val updatedCity = city.copy(name = "Mega Warszawa", latitude = 52.2300)

        repository.updateCity(updatedCity)

        val retrievedCity = repository.getCityByKey("274663")
        assertThat(retrievedCity).isNotNull()
        assertThat(retrievedCity?.name).isEqualTo("Mega Warszawa")
        assertThat(retrievedCity?.latitude).isEqualTo(52.2300)
    }
}