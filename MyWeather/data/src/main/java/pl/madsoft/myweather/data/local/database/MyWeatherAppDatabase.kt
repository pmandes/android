package pl.madsoft.myweather.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.madsoft.myweather.data.local.dao.CityDAO
import pl.madsoft.myweather.data.local.entity.CityEntity

@Database(
    entities = [CityEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MyWeatherAppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDAO

}