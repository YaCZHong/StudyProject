package com.czh.crash.info

import androidx.annotation.Keep

@Keep
internal data class DeviceInfo(
    val deviceBrand: String,
    val deviceModel: String,
    val osVersion: String
)