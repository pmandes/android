package pl.madsoft.myweather.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import pl.madsoft.myweather.presentation.R
import pl.madsoft.myweather.presentation.ui.currenttab.CurrentWeatherScreen
import pl.madsoft.myweather.presentation.ui.historytab.SearchHistoryScreen
import pl.madsoft.myweather.presentation.ui.search.SearchBar
import pl.madsoft.myweather.presentation.ui.search.SearchedCitiesScreen
import pl.madsoft.myweather.presentation.viewmodel.MainIntent
import pl.madsoft.myweather.presentation.viewmodel.MainViewModel
import pl.madsoft.myweather.presentation.viewmodel.MainViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {

    val viewState by viewModel.viewState.collectAsState()

    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    var selectedTabIndex by remember { mutableStateOf(0) }

    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                onSearch = {
                    Log.d("Search", "$searchQuery")
                    viewModel.processIntent(MainIntent.SearchCity(searchQuery.text))
                }
            )
        }
    ) { innerPadding ->

        Log.d("MainScreen", "viewState -> $viewState")

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {

            when (viewState) {

                is MainViewState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is MainViewState.Error -> {
                    ErrorScreen(
                        message = (viewState as MainViewState.Error).message,
                        onClick = {
                            viewModel.processIntent(MainIntent.ShowCurrentWeather)
                        }
                    )
                }

                is MainViewState.ShowSearchedCityList -> {
                    SearchedCitiesScreen(
                        cities = (viewState as MainViewState.ShowSearchedCityList).cities,
                        onCitySelected = { selectedCity ->
                            viewModel.processIntent(MainIntent.SelectCity(selectedCity))
                            searchQuery = TextFieldValue("")
                            focusManager.clearFocus()
                        }
                    )
                }

                is MainViewState.Idle,
                is MainViewState.ShowCurrentWeather,
                is MainViewState.ShowSearchHistory -> {
                    TabView(
                        viewState = viewState,
                        viewModel = viewModel,
                        selectedTabIndex = selectedTabIndex,
                        onTabSelected = { index ->
                            selectedTabIndex = index
                            if (selectedTabIndex == 0) {
                                viewModel.processIntent(MainIntent.ShowCurrentWeather)
                            }
                            if (selectedTabIndex == 1) {
                                viewModel.processIntent(MainIntent.ShowSearchHistory)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TabView(
    viewState: MainViewState,
    viewModel: MainViewModel,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf(
        stringResource(R.string.current_weather),
        stringResource(R.string.search_history)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                    text = { Text(title) }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> CurrentWeatherScreen(state = viewState)
            1 -> SearchHistoryScreen(
                state = viewState,
                onCitySelected = { selectedCity ->
                    viewModel.processIntent(MainIntent.SelectCity(selectedCity))
                    onTabSelected(0)
                }
            )
        }
    }
}
