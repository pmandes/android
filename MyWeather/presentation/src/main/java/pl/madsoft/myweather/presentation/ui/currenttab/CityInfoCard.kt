package pl.madsoft.myweather.presentation.ui.currenttab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.madsoft.myweather.domain.model.City
import pl.madsoft.myweather.presentation.R
import pl.madsoft.myweather.presentation.ui.theme.getCardGradientColors
import pl.madsoft.myweather.presentation.ui.theme.temperatureTextStyle

@Composable
fun CityInfoCard(city: City, currentTemperature: Double) {

    val gradientColors = getCardGradientColors("City")

    CardWithGradient(
        gradientColors = gradientColors,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Text(
                    text = stringResource(R.string.city_country_format, city.name, city.country),
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = stringResource(R.string.region_format, city.region))
                        Text(
                            text = stringResource(R.string.administrative_area_format, city.administrativeArea),
                            style = MaterialTheme.typography.bodySmall
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.LocationOn,
                                contentDescription = null, // Ikona dekoracyjna
                                modifier = Modifier.size(16.dp),
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = stringResource(R.string.coordinates_format, city.latitude, city.longitude),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }

                    Text(
                        text = "${currentTemperature}°C",
                        style = temperatureTextStyle(currentTemperature)
                    )
                }
            }
        }
    )
}
