package edu.wcupa.finalproject.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import edu.wcupa.finalproject.database.model.Entry
import edu.wcupa.finalproject.database.model.Goal
import edu.wcupa.finalproject.database.model.Session
import edu.wcupa.finalproject.database.model.User


@Database(entities = [User::class, Session::class, Goal::class, Entry::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun sessionDao(): SessionDao
    abstract fun goalDao(): GoalDao
    abstract fun entryDao(): EntryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}