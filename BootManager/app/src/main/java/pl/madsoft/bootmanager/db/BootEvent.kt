package pl.madsoft.bootmanager.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BootEvent(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val bootTime: Long = System.currentTimeMillis(),
    val date: String
)