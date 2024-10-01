package pl.madsoft.myweather.presentation.ui.currenttab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.madsoft.myweather.domain.model.Forecast
import pl.madsoft.myweather.presentation.R
import pl.madsoft.myweather.presentation.ui.common.CardHeader
import pl.madsoft.myweather.presentation.ui.common.CardWithGradient
import pl.madsoft.myweather.presentation.ui.theme.getCardGradientColors

@Composable
fun ForecastCard(forecast: Forecast) {

    val gradientColors = getCardGradientColors("Forecast")

    CardWithGradient(
        gradientColors = gradientColors,
        content = {
            Column(
                content =
                {
                    CardHeader(
                        title = stringResource(R.string.forecast),
                    )

                    Text(
                        text = forecast.headline,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )

                    forecast.dailyForecasts.forEach { dailyForecast ->
                        DailyForecastItem(dailyForecast = dailyForecast)
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            )
        }
    )
}
