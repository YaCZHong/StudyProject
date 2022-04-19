package com.czh.crash.db

import androidx.room.*

@Dao
interface CrashDao {
    @Query("SELECT * FROM crash")
    fun getAll(): List<Crash>

    @Query("SELECT * FROM crash where uid = :uid")
    fun getCrash(uid: Int): Crash

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCrash(crash: Crash)

    @Delete
    fun deleteCrash(crash: Crash)
}