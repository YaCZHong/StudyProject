package com.czh.crash

class CrashConfig private constructor(val useDefaultHandler: Boolean, val dbName: String) {
    class Builder {
        private var useDefaultHandler: Boolean = false
        private var dbName: String = DEFAULT_CRASH_DB_NAME

        fun setUseDefaultHandler(used: Boolean): Builder {
            this.useDefaultHandler = used
            return this
        }

        fun setDBName(dbName: String): Builder {
            this.dbName = dbName
            return this
        }

        fun build(): CrashConfig {
            return CrashConfig(useDefaultHandler, dbName)
        }
    }
}