package com.czh.studyproject.ui.activity.rv

import android.os.Bundle
import android.view.LayoutInflater
import com.czh.studyproject.databinding.ActivityRvBrokenLineBinding
import com.czh.studyproject.ui.base.BaseActivity

class RvBrokenLineActivity : BaseActivity<ActivityRvBrokenLineBinding>() {

    override fun initBinding(layoutInflater: LayoutInflater): ActivityRvBrokenLineBinding {
        return ActivityRvBrokenLineBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {

    }
}