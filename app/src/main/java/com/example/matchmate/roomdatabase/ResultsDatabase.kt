package com.example.matchmate.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.matchmate.dao.ResultsDao
import com.example.matchmate.model.CardModelTransformation
import com.example.matchmate.roomdatabase.converters.Converters

@Database(
    entities = [CardModelTransformation::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ResultsDatabase : RoomDatabase() {
    abstract fun resultsDataDao(): ResultsDao

    companion object {
        private const val DATABASE_NAME = "app_database"

        @Volatile
        private var INSTANCE: ResultsDatabase? = null

        fun getDatabase(context: Context): ResultsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResultsDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
