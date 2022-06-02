package com.czh.studyproject.vm

import androidx.lifecycle.LiveData
import com.czh.http.base.HttpBaseViewModel
import com.czh.http.response.ApiResult
import com.czh.studyproject.http.model.UserBean
import com.czh.studyproject.http.repo.LoginRepo

class LoginVM: HttpBaseViewModel() {

    fun login(userName: String, password: String):LiveData<ApiResult<UserBean>>{
        return launch { LoginRepo.login(userName, password) }
    }
}