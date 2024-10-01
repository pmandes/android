package pl.madsoft.myweather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pl.madsoft.myweather.domain.usecase.SearchCityUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<MainViewState>(MainViewState.Idle)
    val viewState: StateFlow<MainViewState> get() = _viewState

    fun processIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.SearchCity -> {
                searchCities(intent.query)
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