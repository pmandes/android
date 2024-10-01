package pl.madsoft.myweather.presentation.ui.currenttab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import pl.madsoft.myweather.domain.model.Weather
import pl.madsoft.myweather.presentation.R
import pl.madsoft.myweather.presentation.ui.common.CardHeader
import pl.madsoft.myweather.presentation.ui.common.CardWithGradient
import pl.madsoft.myweather.presentation.ui.theme.getCardGradientColors

@Composable
fun WeatherInfoCard(weather: Weather) {

    val gradientColors = getCardGradientColors("Weather")

    CardWithGradient(
        gradientColors = gradientColors,
        content = {
            Column(
                content = {
                    CardHeader(title = stringResource(R.string.weather_details))

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        modifier = Modifier.padding(16.dp),
                        content = {
                            Text(
                                text = stringResource(
                                    R.string.feels_like_temperature_format,
                                    weather.feelsLikeTemperature
                                )
                            )
                            Text(text = stringResource(R.string.humidity_format, weather.humidity))
                            Text(
                                text = stringResource(
                                    R.string.cloudiness_format,
                                    weather.cloudiness
                                )
                            )
                            Text(
                                text = stringResource(
                                    R.string.precipitation_possible_format,
                                    if (weather.precipitationPossible) stringResource(R.string.yes) else stringResource(
                                        R.string.no
                                    )
                                )
                            )
                            Text(
                                text = stringResource(
                                    R.string.visibility_format,
                                    weather.visibility
                                )
                            )
                            Text(
                                text = stringResource(
                                    R.string.wind_speed_format,
                                    weather.windSpeed
                                )
                            )
                            Text(
                                text = stringResource(
                                    R.string.wind_direction_format,
                                    weather.windDirection
                                )
                            )
                        }
                    )
                }
            )
        }
    )
}