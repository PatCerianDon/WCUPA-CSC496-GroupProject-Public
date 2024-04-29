package edu.wcupa.finalproject.database.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entries")
data class Entry(
    @PrimaryKey(autoGenerate = true) val entryId: Int = 0,
    val sessionId: Int,
    val content: String
)