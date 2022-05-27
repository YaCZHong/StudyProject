package com.czh.studyproject.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.czh.crash.ui.activity.CrashListActivity
import com.czh.http.response.ApiErrorResponse
import com.czh.http.response.ApiFailureResponse
import com.czh.http.response.ApiSuccessResponse
import com.czh.studyproject.databinding.ActivityMainBinding
import com.czh.studyproject.http.exception.ApiExceptionHandlerImpl
import com.czh.studyproject.ui.base.BaseActivity
import com.czh.studyproject.vm.LoginVM
import com.czh.xhlib.toast.toast

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val loginVM by viewModels<LoginVM>()

    override fun initBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {
        binding.apply {
            btnCrash.setOnClickListener {
                CrashListActivity.actionStart(this@MainActivity)
            }
            btnUi.setOnClickListener {
                gotoActivity(UIActivity::class.java)
            }
        }
        loginVM.login("牛儿不吃草czhlxm", "ch551569lxm").observe(this, Observer {
            when (it) {
                is ApiSuccessResponse -> {
                    toast(it.body.data.toString())
                }
                is ApiFailureResponse -> {
                    ApiExceptionHandlerImpl.handleException(it.apiCode)
                }
                is ApiErrorResponse -> {
                    toast(it.errorMsg)
                }
            }
        })
    }
}