package edu.wcupa.finalproject.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import edu.wcupa.finalproject.database.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    suspend fun getUserByUsernameAndPassword(username: String, password: String): User?

    @Query("SELECT * FROM users WHERE userId = :userId")
    suspend fun getUserById(userId: Int): User?
}