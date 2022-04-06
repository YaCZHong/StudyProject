package com.czh.studyproject.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import com.czh.studyproject.databinding.ActivityMainBinding
import com.czh.studyproject.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun initBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {
        binding.btnUi.setOnClickListener {
            gotoActivity(UIActivity::class.java)
        }
    }
}