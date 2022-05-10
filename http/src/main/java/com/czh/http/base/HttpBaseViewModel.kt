package com.czh.http.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.czh.http.response.ApiResponse
import com.czh.http.response.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @Description 带有网络请求相关的ViewModel基类
 * @Author CZH
 * @Date 2021/5/11 19:18
 */
abstract class HttpBaseViewModel : ViewModel() {

    protected val TAG = this.javaClass.simpleName

    val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    private var loadingCount: Int = 0

    fun <T : BaseResponse<*>> launch(block: suspend () -> T): LiveData<ApiResponse<T>> {
        val liveData = MutableLiveData<ApiResponse<T>>()
        viewModelScope.launch(Dispatchers.Main) {
            handleLoadingStatus(true)
            try {
                val response = withContext(Dispatchers.IO) {
                    block.invoke()
                }
                liveData.value = ApiResponse.create(response)
            } catch (e: Exception) {
                liveData.value = ApiResponse.create(e)
            } finally {
                handleLoadingStatus(false)
            }
        }
        return liveData
    }

    private fun handleLoadingStatus(loading: Boolean) {
        if (loading) {
            loadingCount++
        } else {
            loadingCount--
        }

        // 获取Loading的状态，非0时显示，0时隐藏
        val show: Boolean = loadingCount != 0

        // 过滤重复
        if (show != loadingLiveData.value) {
            loadingLiveData.value = show
        }
    }
}