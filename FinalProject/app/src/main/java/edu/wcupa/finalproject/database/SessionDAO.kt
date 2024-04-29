package edu.wcupa.finalproject.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import edu.wcupa.finalproject.database.model.Session

@Dao
interface SessionDao {
    @Insert
    suspend fun insertSession(session: Session): Long

    @Query("SELECT * FROM sessions WHERE userId = :userId")
    suspend fun getSessionsForUser(userId: Int): List<Session>
}