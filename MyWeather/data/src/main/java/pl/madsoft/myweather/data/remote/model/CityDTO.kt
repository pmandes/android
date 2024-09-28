package pl.madsoft.myweather.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityDTO(
    @Json(name = "Key")
    val key: String,

    @Json(name = "LocalizedName")
    val name: String,

    @Json(name = "Region")
    val region: RegionDTO,

    @Json(name = "Country")
    val country: CountryDTO,

    @Json(name = "AdministrativeArea")
    val administrativeArea: AdministrativeAreaDTO,

    @Json(name = "GeoPosition")
    val geoPosition: GeoPositionDTO,
)

@JsonClass(generateAdapter = true)
data class RegionDTO(
    @Json(name = "LocalizedName")
    val localizedName: String
)

@JsonClass(generateAdapter = true)
data class CountryDTO(
    @Json(name = "LocalizedName")
    val localizedName: String
)

@JsonClass(generateAdapter = true)
data class AdministrativeAreaDTO(
    @Json(name = "LocalizedName")
    val localizedName: String
)

@JsonClass(generateAdapter = true)
data class GeoPositionDTO(
    @Json(name = "Latitude")
    val latitude: Double,

    @Json(name = "Longitude")
    val longitude: Double
)
