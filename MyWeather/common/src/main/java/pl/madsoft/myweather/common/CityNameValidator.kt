package pl.madsoft.myweather.common

object CityNameValidator {

    // Regular expression for validating city names
    private val cityNameRegex = Regex("^[A-Za-zĄĆĘŁŃÓŚŹŻąćęłńóśźż ]+$")

    /**
     * Checks if the city name is valid.
     *
     * @param input The input text.
     * @return True if the name is valid, otherwise False.
     */
    fun isValidCityName(input: String): Boolean {
        return cityNameRegex.matches(input.trim())
    }
}