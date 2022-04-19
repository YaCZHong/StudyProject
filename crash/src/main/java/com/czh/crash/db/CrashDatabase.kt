package com.czh.crash.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

@Database(entities = [Crash::class], version = 1, exportSchema = false)
abstract class CrashDatabase : RoomDatabase() {
    abstract fun CrashDao(): CrashDao

    companion object {

        private const val DATABASE_DIR = ".CRASH" // 加“.”是为了在文件管理中可以达到初步隐藏的目的
        private var sInstance: CrashDatabase? = null

        fun getInstance(context: Context, dbName: String): CrashDatabase {
            return sInstance ?: synchronized(CrashDatabase::class.java) {
                sInstance ?: Room.databaseBuilder(
                    context,
                    CrashDatabase::class.java,
                    (context.getExternalFilesDir(null)?.absolutePath ?: context.filesDir.absolutePath)
                        .plus(File.separator)
                        .plus(DATABASE_DIR)
                        .plus(File.separator)
                        .plus(dbName)
                ).build().also { sInstance = it }
            }
        }
    }
}
