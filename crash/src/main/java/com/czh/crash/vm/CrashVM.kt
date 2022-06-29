package com.czh.crash.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czh.crash.CrashHandler
import com.czh.crash.db.Crash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class CrashVM : ViewModel() {

    val crashes: LiveData<List<Crash>> = CrashHandler.getCrashDb().CrashDao().getAll()

    fun readCrashFromDB(uid: Int): LiveData<Crash> {
        val liveData = MutableLiveData<Crash>()
        viewModelScope.launch(Dispatchers.IO) {
            val crash = CrashHandler.getCrashDb().CrashDao().getCrash(uid)
            liveData.postValue(crash)
        }
        return liveData
    }
}