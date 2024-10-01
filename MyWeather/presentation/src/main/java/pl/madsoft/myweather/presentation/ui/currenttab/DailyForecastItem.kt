package pl.madsoft.myweather.presentation.ui.currenttab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.madsoft.myweather.domain.model.DailyForecast
import pl.madsoft.myweather.presentation.R
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun DailyForecastItem(dailyForecast: DailyForecast) {

    val dayOfWeek = dailyForecast.date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
    val formattedDate = dailyForecast.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
        )

        Text(
            text = "$dayOfWeek, $formattedDate",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = stringResource(R.string.min_temperature_format, dailyForecast.minTemperature))
        Text(text = stringResource(R.string.max_temperature_format, dailyForecast.maxTemperature))

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(
                R.string.day_condition_format,
                dailyForecast.dayCondition.iconPhrase
            )
        )
        Text(
            text = stringResource(
                R.string.night_condition_format,
                dailyForecast.nightCondition.iconPhrase
            )
        )
    }
}
