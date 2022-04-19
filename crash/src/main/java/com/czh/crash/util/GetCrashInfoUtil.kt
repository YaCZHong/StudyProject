package com.czh.crash.util

import android.content.Context
import com.czh.crash.info.DeviceInfo
import com.czh.crash.info.VersionInfo
import android.os.Build

fun getVersionInfo(context: Context): VersionInfo {
    val packageName = context.packageName
    val versionName = context.packageManager.getPackageInfo(packageName, 0).versionName
    val versionCode = context.packageManager.getPackageInfo(packageName, 0).versionCode
    return VersionInfo(versionName, versionCode)
}

fun getDeviceInfo(): DeviceInfo {
    val deviceBrand = Build.BRAND
    val deviceModel = Build.MODEL
    val osVersion = Build.VERSION.RELEASE
    return DeviceInfo(deviceBrand, deviceModel, osVersion)
}