package pl.madsoft.myweather.presentation.ui.theme

import androidx.compose.ui.graphics.Color

// Kolory dla trybu dziennego
val PastelBlue = Color(0xFFA7C7E7)
val PastelGreen = Color(0xFFA8E6CF)
val PastelYellow = Color(0xFFFFF9C4)
val PastelPink = Color(0xFFFFD1DC)

// Kolory dla trybu nocnego
val DarkPastelBlue = Color(0xFF547A9B)
val DarkPastelGreen = Color(0xFF5B9C87)
val DarkPastelYellow = Color(0xFFC7B06E)
val DarkPastelPink = Color(0xFFC78A97)

// Background
val PastelLightBlue = Color(0xFFF8F9FF)

// Główny kolor czcionki
val TextColorDay  = Color(0xD0143A5B)
val TextColorNight  = Color(0xD0FFFFFF)

// Kolory gradientu dla karty CityInfoCard
val CityCardGradientDay = listOf(PastelPink, PastelYellow)
val CityCardGradientNight = listOf(DarkPastelPink, DarkPastelYellow)

// Kolory gradientu dla karty WeatherInfoCard
val WeatherCardGradientDay = listOf(PastelBlue, PastelGreen)
val WeatherCardGradientNight = listOf(DarkPastelBlue, DarkPastelGreen)

// Kolory gradientu dla karty ForecastCard
val ForecastCardGradientDay = listOf(PastelYellow, PastelPink)
val ForecastCardGradientNight = listOf(DarkPastelYellow, DarkPastelPink)

// Kolory dla temperatur
val HotColorDay = Color(0xA0D32F2F)      // Dark Red
val NormalColorDay = Color(0xA0222222)   // Dark Gray
val ColdColorDay = Color(0xA01976D2)     // Dark Blue

// Kolory dla trybu nocnego
val HotColorNight = Color(0xFFFFCDD2)    // Light Red
val NormalColorNight = Color(0xFFE0E0E0) // Light Gray
val ColdColorNight = Color(0xFFA0DAF9)   // Light Blue