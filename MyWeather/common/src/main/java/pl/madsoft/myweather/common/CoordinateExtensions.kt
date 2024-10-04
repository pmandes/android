package pl.madsoft.myweather.common

import java.util.Locale

/**
 * Provides extension functions and types for geographic coordinate conversions.
 *
 * Includes:
 * - `CoordinateType` enum class for specifying latitude or longitude.
 * - `Double.toDMS` extension function for converting decimal degrees to DMS format.
 */

/**
 * Represents the type of geographic coordinate.
 */
enum class CoordinateType {
    /**
     * Latitude coordinate (north or south of the equator).
     */
    LATITUDE,

    /**
     * Longitude coordinate (east or west of the Prime Meridian).
     */
    LONGITUDE
}

/**
 * Converts a decimal degree value to a DMS (Degrees, Minutes, Seconds) formatted string.
 *
 * **DMS (Degrees, Minutes, Seconds)** is a geographic coordinate format that expresses
 * latitude or longitude as degrees (°), minutes ('), and seconds ("), along with a direction:
 *
 * - For latitude: 'N' (north) or 'S' (south)
 * - For longitude: 'E' (east) or 'W' (west)
 *
 * This format is commonly used in navigation and mapping to provide precise location information.
 *
 * @receiver The decimal degree value to convert.
 * @param type The type of coordinate, either [CoordinateType.LATITUDE] or [CoordinateType.LONGITUDE].
 * @param locale The [Locale] used for formatting the decimal separator in seconds. Defaults to [Locale.US] (dot as decimal separator).
 * @return A string representing the coordinate in DMS format, e.g., "51°10'44.04\"N".
 */
fun Double.toDMS(type: CoordinateType, locale: Locale = Locale.US): String {
    val absValue = kotlin.math.abs(this)
    val degrees = absValue.toInt()
    val minutesDecimal = (absValue - degrees) * 60
    val minutes = minutesDecimal.toInt()
    val seconds = (minutesDecimal - minutes) * 60

    val direction = when (type) {
        CoordinateType.LATITUDE -> if (this >= 0) "N" else "S"
        CoordinateType.LONGITUDE -> if (this >= 0) "E" else "W"
    }

    return String.format(locale, "%d°%d'%.2f\"%s", degrees, minutes, seconds, direction)
}