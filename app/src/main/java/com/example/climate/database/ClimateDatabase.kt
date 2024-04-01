package com.example.climate.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [ClimateEntity::class], version = 1, exportSchema = false)
abstract class ClimateDatabase : RoomDatabase() {

    abstract fun weatherDao(): ClimateDao

    companion object {
        @Volatile
        private var INSTANCE: ClimateDatabase? = null

        fun getDatabase(context: Context): ClimateDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ClimateDatabase::class.java,
                    "weather_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}