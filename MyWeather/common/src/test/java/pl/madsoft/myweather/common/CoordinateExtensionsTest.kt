package pl.madsoft.myweather.common

import kotlin.test.Test
import kotlin.test.assertEquals

class CoordinateConversionTest {

    @Test
    fun testPositiveLatitude() {
        val latitude = 51.1789
        val expected = "51°10'44.04\"N"
        val actual = latitude.toDMS(CoordinateType.LATITUDE)
        assertEquals(expected, actual)
    }

    @Test
    fun testNegativeLatitude() {
        val latitude = -23.5505
        val expected = "23°33'1.80\"S"
        val actual = latitude.toDMS(CoordinateType.LATITUDE)
        assertEquals(expected, actual)
    }

    @Test
    fun testPositiveLongitude() {
        val longitude = 46.6333
        val expected = "46°37'59.88\"E"
        val actual = longitude.toDMS(CoordinateType.LONGITUDE)
        assertEquals(expected, actual)
    }

    @Test
    fun testNegativeLongitude() {
        val longitude = -1.8262
        val expected = "1°49'34.32\"W"
        val actual = longitude.toDMS(CoordinateType.LONGITUDE)
        assertEquals(expected, actual)
    }

    @Test
    fun testZeroLatitude() {
        val latitude = 0.0
        val expected = "0°0'0.00\"N"
        val actual = latitude.toDMS(CoordinateType.LATITUDE)
        assertEquals(expected, actual)
    }

    @Test
    fun testZeroLongitude() {
        val longitude = 0.0
        val expected = "0°0'0.00\"E"
        val actual = longitude.toDMS(CoordinateType.LONGITUDE)
        assertEquals(expected, actual)
    }
}