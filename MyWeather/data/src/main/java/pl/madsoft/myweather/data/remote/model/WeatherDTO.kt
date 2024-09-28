package pl.madsoft.myweather.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDTO(
    @Json(name = "LocalObservationDateTime")
    val localObservationDateTime: String,

    @Json(name = "EpochTime")
    val epochTime: Long,

    @Json(name = "WeatherText")
    val weatherText: String,

    @Json(name = "WeatherIcon")
    val weatherIcon: Int,

    @Json(name = "HasPrecipitation")
    val hasPrecipitation: Boolean,

    @Json(name = "PrecipitationType")
    val precipitationType: String?,

    @Json(name = "IsDayTime")
    val isDayTime: Boolean,

    @Json(name = "Temperature")
    val temperature: Measurement,

    @Json(name = "RealFeelTemperature")
    val realFeelTemperature: MeasurementWithPhrase,

    @Json(name = "RealFeelTemperatureShade")
    val realFeelTemperatureShade: MeasurementWithPhrase,

    @Json(name = "RelativeHumidity")
    val relativeHumidity: Int,

    @Json(name = "IndoorRelativeHumidity")
    val indoorRelativeHumidity: Int,

    @Json(name = "DewPoint")
    val dewPoint: Measurement,

    @Json(name = "Wind")
    val wind: Wind,

    @Json(name = "WindGust")
    val windGust: WindGust,

    @Json(name = "UVIndex")
    val uvIndex: Int,

    @Json(name = "UVIndexText")
    val uvIndexText: String,

    @Json(name = "Visibility")
    val visibility: Measurement,

    @Json(name = "ObstructionsToVisibility")
    val obstructionsToVisibility: String,

    @Json(name = "CloudCover")
    val cloudCover: Int,

    @Json(name = "Ceiling")
    val ceiling: Measurement,

    @Json(name = "Pressure")
    val pressure: Measurement,

    @Json(name = "PressureTendency")
    val pressureTendency: PressureTendency,

    @Json(name = "Past24HourTemperatureDeparture")
    val past24HourTemperatureDeparture: Measurement,

    @Json(name = "ApparentTemperature")
    val apparentTemperature: Measurement,

    @Json(name = "WindChillTemperature")
    val windChillTemperature: Measurement,

    @Json(name = "WetBulbTemperature")
    val wetBulbTemperature: Measurement,

    @Json(name = "WetBulbGlobeTemperature")
    val wetBulbGlobeTemperature: Measurement,

    @Json(name = "Precip1hr")
    val precip1hr: Measurement,

    @Json(name = "PrecipitationSummary")
    val precipitationSummary: PrecipitationSummary,

    @Json(name = "TemperatureSummary")
    val temperatureSummary: TemperatureSummary,

    @Json(name = "MobileLink")
    val mobileLink: String,

    @Json(name = "Link")
    val link: String
)

@JsonClass(generateAdapter = true)
data class Measurement(
    @Json(name = "Metric")
    val metric: UnitValue,

    @Json(name = "Imperial")
    val imperial: UnitValue
)

@JsonClass(generateAdapter = true)
data class MeasurementWithPhrase(
    @Json(name = "Metric")
    val metric: UnitValueWithPhrase,

    @Json(name = "Imperial")
    val imperial: UnitValueWithPhrase
)

@JsonClass(generateAdapter = true)
data class UnitValue(
    @Json(name = "Value")
    val value: Double,

    @Json(name = "Unit")
    val unit: String,

    @Json(name = "UnitType")
    val unitType: Int
)

@JsonClass(generateAdapter = true)
data class UnitValueWithPhrase(
    @Json(name = "Value")
    val value: Double,

    @Json(name = "Unit")
    val unit: String,

    @Json(name = "UnitType")
    val unitType: Int,

    @Json(name = "Phrase")
    val phrase: String
)

@JsonClass(generateAdapter = true)
data class Wind(
    @Json(name = "Direction")
    val direction: Direction,

    @Json(name = "Speed")
    val speed: Measurement
)

@JsonClass(generateAdapter = true)
data class WindGust(
    @Json(name = "Speed")
    val speed: Measurement
)

@JsonClass(generateAdapter = true)
data class Direction(
    @Json(name = "Degrees")
    val degrees: Int,

    @Json(name = "Localized")
    val localized: String,

    @Json(name = "English")
    val english: String
)

@JsonClass(generateAdapter = true)
data class PressureTendency(
    @Json(name = "LocalizedText")
    val localizedText: String,

    @Json(name = "Code")
    val code: String
)

@JsonClass(generateAdapter = true)
data class PrecipitationSummary(
    @Json(name = "Precipitation")
    val precipitation: Measurement,

    @Json(name = "PastHour")
    val pastHour: Measurement,

    @Json(name = "Past3Hours")
    val past3Hours: Measurement,

    @Json(name = "Past6Hours")
    val past6Hours: Measurement,

    @Json(name = "Past9Hours")
    val past9Hours: Measurement,

    @Json(name = "Past12Hours")
    val past12Hours: Measurement,

    @Json(name = "Past18Hours")
    val past18Hours: Measurement,

    @Json(name = "Past24Hours")
    val past24Hours: Measurement
)

@JsonClass(generateAdapter = true)
data class TemperatureSummary(
    @Json(name = "Past6HourRange")
    val past6HourRange: TemperatureRange,

    @Json(name = "Past12HourRange")
    val past12HourRange: TemperatureRange,

    @Json(name = "Past24HourRange")
    val past24HourRange: TemperatureRange
)

@JsonClass(generateAdapter = true)
data class TemperatureRange(
    @Json(name = "Minimum")
    val minimum: Measurement,

    @Json(name = "Maximum")
    val maximum: Measurement
)