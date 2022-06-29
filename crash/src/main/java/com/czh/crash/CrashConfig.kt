package com.czh.crash

import android.app.Application
import com.czh.crash.CrashConstants.DEFAULT_CRASH_DB_DIR
import com.czh.crash.CrashConstants.DEFAULT_CRASH_DB_NAME
import java.io.File

class CrashConfig private constructor(
    val application: Application,
    val useDefaultHandler: Boolean,
    val dbDir: String,
    val dbName: String,
    var userId: String
) {
    class Builder(private val application: Application) {
        private var useDefaultHandler: Boolean = false
        private var dbDir: String =
            application.filesDir.absolutePath.plus(File.separator).plus(DEFAULT_CRASH_DB_DIR)
        private var dbName: String = DEFAULT_CRASH_DB_NAME
        private var userId: String = "unknown"

        fun setUseDefaultHandler(useDefaultHandler: Boolean): Builder {
            this.useDefaultHandler = useDefaultHandler
            return this
        }

        fun setDbDir(dbDir: String): Builder {
            this.dbDir = dbDir
            return this
        }

        fun setDBName(dbName: String): Builder {
            this.dbName = dbName
            return this
        }

        fun setUserId(userId: String): Builder {
            this.userId = userId
            return this
        }

        fun build(): CrashConfig {
            return CrashConfig(application, useDefaultHandler, dbDir, dbName, userId)
        }
    }
}