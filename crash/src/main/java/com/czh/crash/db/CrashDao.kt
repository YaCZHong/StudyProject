package com.czh.crash.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
internal interface CrashDao {
    @Query("SELECT * FROM crash")
    fun getAll(): LiveData<List<Crash>>

    @Query("SELECT * FROM crash where uid = :uid")
    suspend fun getCrash(uid: Int): Crash

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrash(crash: Crash)

    @Delete
    suspend fun deleteCrash(crash: Crash)
}