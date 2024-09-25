package pl.madsoft.myweather.data.mapper

import pl.madsoft.myweather.data.remote.model.CityDTO
import pl.madsoft.myweather.domain.model.City


fun CityDTO.toDomainModel(): City {
    return City(
        key = this.key,
        name = this.name,
        region = this.region.localizedName,
        country = this.country.localizedName,
        administrativeArea = this.administrativeArea.localizedName,
        longitude = this.geoPosition.longitude,
        latitude = this.geoPosition.latitude
    )
}