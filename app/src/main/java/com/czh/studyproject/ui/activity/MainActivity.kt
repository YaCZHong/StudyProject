package com.czh.studyproject.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.czh.crash.ui.activity.CrashListActivity
import com.czh.studyproject.databinding.ActivityMainBinding
import com.czh.studyproject.http.observer.ApiResultObserver
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
        loginVM.loadingLiveData.observe(this, Observer { show ->
            if (show) {
                showLoading("请稍候...")
            } else {
                hideLoading()
            }
        })
        loginVM.login("牛儿不吃草czhlx", "czh551569lxm").observe(this, ApiResultObserver(onSuccess = {
            toast(it.toString())
        }))
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}