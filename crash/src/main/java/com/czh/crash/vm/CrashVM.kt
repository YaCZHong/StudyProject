package com.czh.crash.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czh.crash.CrashHandler
import com.czh.crash.db.Crash
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CrashVM : ViewModel() {

    fun readAllCrashFromDB(): LiveData<List<Crash>> {
        val liveData = MutableLiveData<List<Crash>>()
        viewModelScope.launch(Dispatchers.IO) {
            val list = CrashHandler.getCrashDb().CrashDao().getAll()
            liveData.postValue(list)
        }
        return liveData
    }

    fun readCrashFromDB(uid: Int): LiveData<Crash> {
        val liveData = MutableLiveData<Crash>()
        viewModelScope.launch(Dispatchers.IO) {
            val crash = CrashHandler.getCrashDb().CrashDao().getCrash(uid)
            liveData.postValue(crash)
        }
        return liveData
    }
}