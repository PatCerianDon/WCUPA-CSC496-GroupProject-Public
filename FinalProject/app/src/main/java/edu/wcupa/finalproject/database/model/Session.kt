package edu.wcupa.finalproject.database.model
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "sessions",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class Session(
    @PrimaryKey(autoGenerate = true) val sessionId: Int = 0,
    val userId: Int,
    val date: String
)