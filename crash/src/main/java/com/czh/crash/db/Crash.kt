package com.czh.crash.db

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity
internal data class Crash(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo val timestamp: Long,
    @ColumnInfo val userId: String = "",
    @ColumnInfo val versionName: String,
    @ColumnInfo val versionCode: Int,
    @ColumnInfo val deviceBrand: String,
    @ColumnInfo val deviceModel: String,
    @ColumnInfo val osVersion: String,
    @ColumnInfo val thread: String,
    @ColumnInfo val throwableSimple: String,
    @ColumnInfo val throwableDetail: String,
    @ColumnInfo val upload: Int = 0 // 是否已上传(0-未上传; 1-已上传)
)
