package com.czh.studyproject.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import com.czh.studyproject.databinding.ActivityUiBinding
import com.czh.studyproject.ui.activity.rv.RvBrokenLineActivity
import com.czh.studyproject.ui.activity.vp.DynamicVpActivity
import com.czh.studyproject.ui.activity.vp.StaticVpActivity
import com.czh.studyproject.ui.base.BaseActivity
import com.czh.studyproject.ui.dialog.FullScreenDialog

class UIActivity : BaseActivity<ActivityUiBinding>() {

    override fun initBinding(layoutInflater: LayoutInflater): ActivityUiBinding {
        return ActivityUiBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {
        binding.apply {
            btnWeb.setOnClickListener {
                gotoActivity(SimpleWebActivity::class.java)
            }
            btnStaticVp.setOnClickListener {
                gotoActivity(StaticVpActivity::class.java)
            }
            btnDynamicVp.setOnClickListener {
                gotoActivity(DynamicVpActivity::class.java)
            }
            btnRvBrokenLine.setOnClickListener {
                gotoActivity(RvBrokenLineActivity::class.java)
            }
            btnFullScreenDialog.setOnClickListener {
                FullScreenDialog.newInstance().show(supportFragmentManager, "")
            }
            btnSpinner.setOnClickListener {
                gotoActivity(SpinnerActivity::class.java)
            }
        }
    }
}