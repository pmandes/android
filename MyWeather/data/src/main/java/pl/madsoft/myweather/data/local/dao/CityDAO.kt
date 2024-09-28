package pl.madsoft.myweather.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pl.madsoft.myweather.data.local.entity.CityEntity

@Dao
interface CityDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(cities: List<CityEntity>)

    @Query("SELECT * FROM cities")
    suspend fun getAllCities(): List<CityEntity>

    @Query("SELECT * FROM cities WHERE `key` = :key LIMIT 1")
    suspend fun getCityByKey(key: String): CityEntity?

    @Delete
    suspend fun deleteCity(city: CityEntity)

    @Update
    suspend fun updateCity(city: CityEntity)

    @Query("DELETE FROM cities")
    suspend fun deleteAllCities()
}