package pl.madsoft.myweather.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.domain.model.Forecast
import pl.madsoft.myweather.domain.model.Weather
import pl.madsoft.myweather.domain.usecase.IGetForecastUseCase
import pl.madsoft.myweather.domain.usecase.IGetWeatherUseCase
import pl.madsoft.myweather.domain.usecase.ILoadCityUseCase
import pl.madsoft.myweather.domain.usecase.ILoadSavedCitiesUseCase
import pl.madsoft.myweather.domain.usecase.ISaveSelectedCityUseCase
import pl.madsoft.myweather.domain.usecase.ISearchCityUseCase

import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchCityUseCase: ISearchCityUseCase,
    private val getWeatherUseCase: IGetWeatherUseCase,
    private val getForecastUseCase: IGetForecastUseCase,
    private val loadCityUseCase: ILoadCityUseCase,
    private val loadSavedCitiesUseCase: ILoadSavedCitiesUseCase,
    private val saveSelectedCityUseCase: ISaveSelectedCityUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<MainViewState>(MainViewState.Idle)

    private var lastSelectedCity: City? = null
    private var lastWeather: Weather? = null
    private var lastForecast: Forecast? = null
    private var lastSearchHistory: List<City>? = null

    val viewState: StateFlow<MainViewState> get() = _viewState

    private val intentChannel = Channel<MainIntent>(Channel.UNLIMITED)
    val intentsFlow = intentChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            intentsFlow.collect { intent ->
                handleIntent(intent)
            }
        }
    }

    fun processIntent(intent: MainIntent) {
        viewModelScope.launch {
            intentChannel.send(intent)
        }
    }

    private suspend fun handleIntent(intent: MainIntent) {

        Log.d("handleIntent", "intent -> $intent")

        when (intent) {
            is MainIntent.SearchCity -> {
                searchCities(intent.query)
            }

            is MainIntent.ShowSearchHistory -> {
                loadSavedCities()
            }

            is MainIntent.ShowCurrentWeather -> {
                lastSelectedCity?.let { city -> selectCity(city) }
            }

            is MainIntent.SelectCity -> {
                selectCity(intent.city)
            }
        }
    }

    private suspend fun loadLastSelectedCity() {

        // TODO: load last selected city from DB on application start

    }

    private suspend fun loadSavedCities() {

        if (lastSearchHistory != null) {
            _viewState.value = MainViewState.ShowSearchHistory(lastSearchHistory!!)
            return
        }

        try {
            val cities = loadSavedCitiesUseCase()
            _viewState.value = MainViewState.ShowSearchHistory(cities)
        } catch (e: Exception) {
            _viewState.value =
                MainViewState.Error(e.localizedMessage ?: "Error fetching saved cities.")
        }
    }

    private suspend fun selectCity(city: City) {

        lastSelectedCity = city
        _viewState.value = MainViewState.Loading

        try {
            saveSelectedCityUseCase(city)

            val currentWeather = getWeatherUseCase(city.key)
            lastWeather = currentWeather

            val forecast = getForecastUseCase(city.key)
            lastForecast = forecast

            _viewState.value = MainViewState.ShowCurrentWeather(
                city = city,
                weather = currentWeather,
                forecast = forecast
            )
        } catch (e: Exception) {
            _viewState.value = MainViewState.Error(e.localizedMessage ?: "Error fetching weather.")
        }
    }

    private suspend fun searchCities(query: String) {

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
