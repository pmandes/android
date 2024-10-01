package pl.madsoft.myweather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.domain.usecase.GetForecastUseCase
import pl.madsoft.myweather.domain.usecase.GetWeatherUseCase
import pl.madsoft.myweather.domain.usecase.LoadCityUseCase
import pl.madsoft.myweather.domain.usecase.LoadSavedCitiesUseCase
import pl.madsoft.myweather.domain.usecase.SaveSelectedCityUseCase
import pl.madsoft.myweather.domain.usecase.SearchCityUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getForecastUseCase: GetForecastUseCase,
    private val loadCityUseCase: LoadCityUseCase,
    private val loadSavedCitiesUseCase: LoadSavedCitiesUseCase,
    private val saveSelectedCityUseCase: SaveSelectedCityUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val viewState: StateFlow<MainViewState> get() = _viewState

    var selectedTabIndex = MutableStateFlow(0)

    init {
        processIntent(MainIntent.LoadLastSelectedCity)
        processIntent(MainIntent.LoadSavedCityList)
    }

    fun processIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.SearchCity -> {
                searchCities(intent.query)
            }
            is MainIntent.LoadSavedCityList -> {
                loadSavedCities()
            }
            is MainIntent.LoadLastSelectedCity -> {
                loadLastSelectedCity()
            }
            is MainIntent.SelectCity -> {
                selectCity(intent.city)
            }
            is MainIntent.Idle -> {
                _viewState.value = MainViewState.Idle
            }
        }
    }

    private fun loadLastSelectedCity() {

    }

    private fun loadSavedCities() {
        viewModelScope.launch {
            try {
                val cities = loadSavedCitiesUseCase()
                _viewState.value = MainViewState.ShowSearchHistory(cities)
            } catch (e: Exception) {
                _viewState.value = MainViewState.Error(e.localizedMessage ?: "Error fetching saved cities.")
            }
        }
    }

    private fun selectCity(city: City) {
        viewModelScope.launch {

            _viewState.value = MainViewState.Loading

            try {
                saveSelectedCityUseCase(city)

                val currentWeather = getWeatherUseCase(city.key)
                val forecast = getForecastUseCase(city.key)

                val cities = loadSavedCitiesUseCase()

                _viewState.value = MainViewState.ShowSearchHistory(
                    history = cities
                )

                _viewState.value = MainViewState.ShowCurrentWeather(
                    city = city,
                    weather = currentWeather,
                    forecast = forecast
                )

                selectedTabIndex.value = 0

            } catch (e: Exception) {
                _viewState.value = MainViewState.Error(e.localizedMessage ?: "Error fetching weather.")
            }
        }
    }

    private fun searchCities(query: String) {
        viewModelScope.launch {
            _viewState.value = MainViewState.Loading

            try {
                val cities = searchCityUseCase(query)

                if (cities.isNotEmpty()) {
                    _viewState.value = MainViewState.ShowSearchedCityList(cities)
                } else {
                    _viewState.value = MainViewState.Error("No cities matching the query were found.")
                }

            } catch (e: Exception) {
                _viewState.value = MainViewState.Error(e.localizedMessage ?: "An error occurred.")
            }
        }
    }
}
