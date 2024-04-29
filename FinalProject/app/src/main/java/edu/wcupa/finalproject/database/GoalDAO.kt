package edu.wcupa.finalproject.database
import androidx.room.Dao
import androidx.room.Insert
import edu.wcupa.finalproject.database.model.Goal

@Dao
interface GoalDao {
    @Insert
    suspend fun insertGoal(goal: Goal): Long
}