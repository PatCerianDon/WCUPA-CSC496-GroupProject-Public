package edu.wcupa.finalproject.database
import androidx.room.Dao
import androidx.room.Insert
import edu.wcupa.finalproject.database.model.Entry

@Dao
interface EntryDao {
    @Insert
    suspend fun insertEntry(entry: Entry): Long
}