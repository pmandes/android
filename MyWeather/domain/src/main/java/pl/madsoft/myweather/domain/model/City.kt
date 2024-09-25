package pl.madsoft.myweather.domain.model

data class City(
    val key: String,
    val name: String,
    val region: String,
    val country: String,
    val administrativeArea: String,
    val latitude: Double,
    val longitude: Double
)