package com.wangmir.calliope.adapters.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wangmir.calliope.domain.entities.DayLog

@Database(
    entities = [DayLog::class],
    version = 1,
    exportSchema = false,
)
abstract class LocalDatabase: RoomDatabase() {
    abstract val dayLogDao: DayLogDao

    companion object {
        fun getDatabase(context: Context): LocalDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                LocalDatabase::class.java,
                "daylog_db.db"
            ).build()
        }

        fun getInMemoryDatabase(context: Context): LocalDatabase {
            return Room.inMemoryDatabaseBuilder(
                context.applicationContext,
                LocalDatabase::class.java
            ).build()
        }
    }
}
