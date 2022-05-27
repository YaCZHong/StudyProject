package com.czh.studyproject.vm

import androidx.lifecycle.LiveData
import com.czh.http.base.HttpBaseViewModel
import com.czh.http.response.ApiResponse
import com.czh.studyproject.http.model.response.LoginResponse
import com.czh.studyproject.http.repo.LoginRepo

class LoginVM: HttpBaseViewModel() {

    fun login(userName: String, password: String):LiveData<ApiResponse<LoginResponse>>{
        return launch { LoginRepo.login(userName, password) }
    }
}