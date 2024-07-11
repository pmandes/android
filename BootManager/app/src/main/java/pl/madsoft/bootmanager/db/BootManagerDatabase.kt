package pl.madsoft.bootmanager.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BootEvent::class], version = 1)
abstract class BootManagerDatabase : RoomDatabase() {

    abstract fun bootEventDao(): BootEventDAO

    companion object {

        @Volatile
        private var INSTANCE: BootManagerDatabase? = null

        fun getDatabase(context: Context): BootManagerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BootManagerDatabase::class.java,
                    "boot_manager_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}