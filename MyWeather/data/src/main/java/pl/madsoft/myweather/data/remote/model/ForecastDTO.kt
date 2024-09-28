package pl.madsoft.myweather.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastDTO(
    @Json(name = "Headline")
    val headline: HeadlineDTO,

    @Json(name = "DailyForecasts")
    val dailyForecasts: List<DailyForecastDTO>
)

@JsonClass(generateAdapter = true)
data class HeadlineDTO(
    @Json(name = "EffectiveDate")
    val effectiveDate: String,

    @Json(name = "EffectiveEpochDate")
    val effectiveEpochDate: Long,

    @Json(name = "Severity")
    val severity: Int,

    @Json(name = "Text")
    val text: String,

    @Json(name = "Category")
    val category: String,

    @Json(name = "EndDate")
    val endDate: String,

    @Json(name = "EndEpochDate")
    val endEpochDate: Long,

    @Json(name = "MobileLink")
    val mobileLink: String,

    @Json(name = "Link")
    val link: String
)

@JsonClass(generateAdapter = true)
data class DailyForecastDTO(
    @Json(name = "Date")
    val date: String,

    @Json(name = "EpochDate")
    val epochDate: Long,

    @Json(name = "Temperature")
    val temperature: TemperatureDTO,

    @Json(name = "Day")
    val day: DayNightDTO,

    @Json(name = "Night")
    val night: DayNightDTO,

    @Json(name = "Sources")
    val sources: List<String>,

    @Json(name = "MobileLink")
    val mobileLink: String,

    @Json(name = "Link")
    val link: String
)

@JsonClass(generateAdapter = true)
data class TemperatureDTO(
    @Json(name = "Minimum")
    val minimum: TemperatureDetailDTO,

    @Json(name = "Maximum")
    val maximum: TemperatureDetailDTO
)

@JsonClass(generateAdapter = true)
data class TemperatureDetailDTO(
    @Json(name = "Value")
    val value: Double,

    @Json(name = "Unit")
    val unit: String,

    @Json(name = "UnitType")
    val unitType: Int
)

@JsonClass(generateAdapter = true)
data class DayNightDTO(
    @Json(name = "Icon")
    val icon: Int,

    @Json(name = "IconPhrase")
    val iconPhrase: String,

    @Json(name = "HasPrecipitation")
    val hasPrecipitation: Boolean,

    @Json(name = "PrecipitationType")
    val precipitationType: String? = null,

    @Json(name = "PrecipitationIntensity")
    val precipitationIntensity: String? = null
)