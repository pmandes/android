package pl.madsoft.myweather.presentation.ui.historytab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.presentation.R
import pl.madsoft.myweather.presentation.ui.common.CityItem
import pl.madsoft.myweather.presentation.viewmodel.MainViewState

@Composable
fun SearchHistoryScreen(
        state: MainViewState,
        onCitySelected: (City) -> Unit
) {

    when (state) {
        is MainViewState.ShowSearchHistory -> {

            val cities = state.history

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(cities) { city ->
                    CityItem(
                        city = city,
                        onClick = {
                            onCitySelected(city)
                        }
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
        else -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = stringResource(R.string.no_saved_cities_data))
            }
        }
    }
}
