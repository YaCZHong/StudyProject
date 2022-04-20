package com.czh.crash.db

import androidx.room.*

@Dao
interface CrashDao {
    @Query("SELECT * FROM crash")
    suspend fun getAll(): List<Crash>

    @Query("SELECT * FROM crash where uid = :uid")
    suspend fun getCrash(uid: Int): Crash

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrash(crash: Crash)

    @Delete
    suspend fun deleteCrash(crash: Crash)
}