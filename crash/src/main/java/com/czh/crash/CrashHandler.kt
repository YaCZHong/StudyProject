package com.czh.crash

import android.util.Log
import com.czh.crash.db.Crash
import com.czh.crash.db.CrashDatabase
import com.czh.crash.info.DeviceInfo
import com.czh.crash.info.VersionInfo
import com.czh.crash.util.getDeviceInfo
import com.czh.crash.util.getVersionInfo
import kotlinx.coroutines.*
import kotlin.system.exitProcess

/**
 * @Description
 * @Author CZH
 * @Date 2021/5/11 09:49
 */
object CrashHandler : Thread.UncaughtExceptionHandler {

    private val defaultHandler by lazy { Thread.getDefaultUncaughtExceptionHandler() }

    private var config: CrashConfig? = null
    private var database: CrashDatabase? = null

    fun init(config: CrashConfig) {
        this.config = config
        this.database = CrashDatabase.getInstance(config)
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        val deviceInfo = getDeviceInfo()
        val versionInfo = getVersionInfo(config!!.application)
        handleException(deviceInfo, versionInfo, thread, throwable)
    }

    private fun handleException(
        deviceInfo: DeviceInfo,
        versionInfo: VersionInfo,
        thread: Thread,
        throwable: Throwable
    ) {
        if (config!!.useDefaultHandler && defaultHandler != null) {
            defaultHandler.uncaughtException(thread, throwable)
        } else {
            runBlocking {
                Crash(
                    timestamp = System.currentTimeMillis(),
                    userId = config!!.userId,
                    versionName = versionInfo.versionName,
                    versionCode = versionInfo.versionCode,
                    deviceBrand = deviceInfo.deviceBrand,
                    deviceModel = deviceInfo.deviceModel,
                    osVersion = deviceInfo.osVersion,
                    thread = thread.name,
                    throwableSimple = throwable.toString(),
                    throwableDetail = Log.getStackTraceString(throwable)
                ).apply {
                    database!!.CrashDao().insertCrash(this)
                    database!!.close()
                }
            }
            exitApp()
        }
    }

    private fun exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid())
        exitProcess(-1)
    }

    fun setUserId(userId: String) {
        checkNotNull(config) {
            R.string.not_init_hint
        }.userId = userId
    }

    internal fun getCrashDb(): CrashDatabase {
        return checkNotNull(database) {
            R.string.not_init_hint
        }
    }
}