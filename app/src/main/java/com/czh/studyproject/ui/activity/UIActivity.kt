package com.czh.studyproject.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import com.czh.studyproject.databinding.ActivityUiBinding
import com.czh.studyproject.ui.base.BaseActivity

class UIActivity : BaseActivity<ActivityUiBinding>() {

    override fun initBinding(layoutInflater: LayoutInflater): ActivityUiBinding {
        return ActivityUiBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {
        binding.apply {
            btnStaticVp.setOnClickListener {
                gotoActivity(StaticVpActivity::class.java)
            }
            btnDynamicVp.setOnClickListener {
                gotoActivity(DynamicVpActivity::class.java)
            }
        }
    }
}