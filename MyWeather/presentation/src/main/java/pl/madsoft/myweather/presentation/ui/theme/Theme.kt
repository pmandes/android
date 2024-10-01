package pl.madsoft.myweather.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle


private val LightColorScheme = lightColorScheme(
    primary = DarkPastelBlue,
    onPrimary = TextColorDay,
    secondary = PastelGreen,
    onSecondary = TextColorDay,
    background = PastelLightBlue,
    onBackground = TextColorDay,
    surface = PastelLightBlue,
    onSurface = TextColorDay,
)

private val DarkColorScheme = darkColorScheme(
    primary = PastelBlue,
    onPrimary = TextColorNight,
    secondary = DarkPastelGreen,
    onSecondary = TextColorNight,
    background = Color.Black,
    onBackground = TextColorNight,
    surface = Color.Black,
    onSurface = TextColorNight,
)

@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}

@Composable
fun getTemperatureColor(temperature: Double): Color {
    val isDarkTheme = isSystemInDarkTheme()
    return when {
        temperature < 10 -> if (isDarkTheme) ColdColorNight else ColdColorDay
        temperature in 10.0..20.0 -> if (isDarkTheme) NormalColorNight else NormalColorDay
        else -> if (isDarkTheme) HotColorNight else HotColorDay
    }
}

@Composable
fun temperatureTextStyle(temperature: Double): TextStyle {
    val color = getTemperatureColor(temperature)
    return MaterialTheme.typography.displayMedium.copy(color = color)
}

@Composable
fun getCardGradientColors(cardType: String): List<Color> {
    val isDarkTheme = isSystemInDarkTheme()
    return when (cardType) {
        "City" -> if (isDarkTheme) CityCardGradientNight else CityCardGradientDay
        "Weather" -> if (isDarkTheme) WeatherCardGradientNight else WeatherCardGradientDay
        "Forecast" -> if (isDarkTheme) ForecastCardGradientNight else ForecastCardGradientDay
        else -> if (isDarkTheme) WeatherCardGradientNight else WeatherCardGradientDay
    }
}
