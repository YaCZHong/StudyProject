package com.czh.crash.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.czh.crash.CrashConfig
import java.io.File

@Database(entities = [Crash::class], version = 1, exportSchema = false)
internal abstract class CrashDatabase : RoomDatabase() {
    abstract fun CrashDao(): CrashDao

    companion object {

        private var sInstance: CrashDatabase? = null

        fun getInstance(config: CrashConfig): CrashDatabase {
            return sInstance ?: synchronized(CrashDatabase::class.java) {
                sInstance ?: Room.databaseBuilder(
                    config.application,
                    CrashDatabase::class.java,
                    config.dbDir.plus(File.separator).plus(config.dbName)
                ).build().also { sInstance = it }
            }
        }
    }
}
