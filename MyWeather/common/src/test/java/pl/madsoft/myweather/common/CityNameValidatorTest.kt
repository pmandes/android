package pl.madsoft.myweather.common

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CityNameValidatorTest {

    // List of valid city names
    private val validCityNames = listOf(
        "Warszawa",
        "Łódź",
        "Świętochłowice",
        "Ząbkowice Śląskie",
        "Żyrardów",
        "Żagań",
        "  Kraków  ",
    )

    // List of invalid city names
    private val invalidCityNames = listOf(
        "Warszawa123",
        "1Warszawa!",
        "P@ryż",
        "Mia$#o",
        "12345",
        "",
        "     "
    )

    @Test
    fun `validate all valid city names`() {
        for (cityName in validCityNames) {
            val result = CityNameValidator.isValidCityName(cityName)
            assertTrue("Expected '$cityName' to be valid.", result)
        }
    }

    @Test
    fun `validate all invalid city names`() {
        for (cityName in invalidCityNames) {
            val result = CityNameValidator.isValidCityName(cityName)
            assertFalse("Expected '$cityName' to be invalid.", result)
        }
    }
}