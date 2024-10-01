package pl.madsoft.myweather.data.mapper

import pl.madsoft.myweather.data.local.entity.CityEntity
import pl.madsoft.myweather.domain.model.City

fun CityEntity.toDomainModel(): City {
    return City(
        key = this.key,
        name = this.name,
        region = this.region,
        country = this.country,
        administrativeArea = this.administrativeArea,
        latitude = this.latitude,
        longitude = this.longitude
    )
}

fun City.toEntity(): CityEntity {
    return CityEntity(
        key = this.key,
        name = this.name,
        region = this.region,
        country = this.country,
        administrativeArea = this.administrativeArea,
        latitude = this.latitude,
        longitude = this.longitude,
        selected = false
    )
}