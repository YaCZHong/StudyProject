package com.czh.studyproject.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.czh.studyproject.ui.dialog.LoadingDialog

/**
* @Description: Activity基类
* @Author: czh
* @CreateDate: 2022/4/5 0:46
*/
abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    private var loading: LoadingDialog? = null

    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initBinding(layoutInflater)
        setContentView(binding.root)
        init(savedInstanceState)
    }

    abstract fun initBinding(layoutInflater: LayoutInflater): T
    abstract fun init(savedInstanceState: Bundle?)

    fun showLoading(msg: String) {
        loading?.dismiss()
        LoadingDialog(msg).also { loading = it }.show(supportFragmentManager, "")
    }

    fun hideLoading() {
        loading?.dismiss()
    }
}