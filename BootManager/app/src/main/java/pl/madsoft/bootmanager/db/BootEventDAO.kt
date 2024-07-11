package pl.madsoft.bootmanager.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BootEventDAO {
    @Insert
    suspend fun insertBootEvent(bootEvent: BootEvent)

    @Query("SELECT COUNT(*) FROM BootEvent WHERE date = :date")
    suspend fun getBootCountForDate(date: String): Int
}