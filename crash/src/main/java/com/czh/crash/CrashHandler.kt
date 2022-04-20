package com.czh.crash

import android.app.Application
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

    private val timestamp
        get() = System.currentTimeMillis()

    private val mDefaultHandler by lazy { Thread.getDefaultUncaughtExceptionHandler() }

    private lateinit var mApplication: Application
    private lateinit var mCrashConfig: CrashConfig
    private lateinit var mCrashDatabase: CrashDatabase

    private var inited = false
    private var userId: String = "" // 如有需要，可以在账号登录成功后调用setUserId设置，方便后期根据用户Id查找问题

    @Synchronized
    fun init(application: Application, crashConfig: CrashConfig) {
        if (inited) {
            Log.e(CRASH_LOG_TAG, "CrashHandler already inited!")
            return
        }
        mApplication = application
        mCrashConfig = crashConfig
        mCrashDatabase =
            CrashDatabase.getInstance(application.applicationContext, mCrashConfig.dbName)
        Thread.setDefaultUncaughtExceptionHandler(this)
        inited = true
        Log.e(CRASH_LOG_TAG, "CrashHandler init successful!")
    }

    override fun uncaughtException(thread: Thread, throwable: Throwable) {
        val deviceInfo = getDeviceInfo()
        val versionInfo = getVersionInfo(mApplication.applicationContext)
        handleException(deviceInfo, versionInfo, thread, throwable)
    }

    private fun handleException(
        deviceInfo: DeviceInfo,
        versionInfo: VersionInfo,
        thread: Thread,
        throwable: Throwable
    ) {
        if (mCrashConfig.useDefaultHandler && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, throwable)
        } else {
            runBlocking {
                Crash(
                    timestamp = timestamp,
                    userId = userId,
                    versionName = versionInfo.versionName,
                    versionCode = versionInfo.versionCode,
                    deviceBrand = deviceInfo.deviceBrand,
                    deviceModel = deviceInfo.deviceModel,
                    osVersion = deviceInfo.osVersion,
                    thread = thread.name,
                    throwableSimple = throwable.toString(),
                    throwableDetail = Log.getStackTraceString(throwable)
                ).apply {
                    mCrashDatabase.CrashDao().insertCrash(this)
                    mCrashDatabase.close()
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
        this.userId = userId
    }

    fun getCrashDb(): CrashDatabase {
        check(inited) {
            "Please call CrashHandler init() first!"
        }
        return mCrashDatabase
    }
}