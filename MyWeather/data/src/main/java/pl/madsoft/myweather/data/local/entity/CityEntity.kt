package pl.madsoft.myweather.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey val key: String,
    val name: String,
    val region: String,
    val country: String,
    val administrativeArea: String,
    val latitude: Double,
    val longitude: Double,
    val selected: Boolean
)