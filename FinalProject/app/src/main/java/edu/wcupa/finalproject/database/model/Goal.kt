package edu.wcupa.finalproject.database.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goals")
data class Goal(
    @PrimaryKey(autoGenerate = true) val goalId: Int = 0,
    val sessionId: Int,
    val description: String,
    val dateStart: String,
    val dateEnd: String,
    val isComplete: Boolean
)

