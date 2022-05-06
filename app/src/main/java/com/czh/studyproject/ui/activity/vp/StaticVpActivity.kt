package com.czh.studyproject.ui.activity.vp

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.czh.studyproject.databinding.ActivityStaticVpBinding
import com.czh.studyproject.ui.adapter.vp.StaticVpAdapter
import com.czh.studyproject.ui.base.BaseActivity
import com.czh.studyproject.ui.fragment.TextFragment

class StaticVpActivity : BaseActivity<ActivityStaticVpBinding>() {
    override fun initBinding(layoutInflater: LayoutInflater): ActivityStaticVpBinding {
        return ActivityStaticVpBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {
        binding.apply {
            val fragments = listOf<Fragment>(
                TextFragment.newInstance("1"),
                TextFragment.newInstance("2"),
                TextFragment.newInstance("3")
            )
            vpStatic.adapter = StaticVpAdapter(this@StaticVpActivity, fragments)
        }
    }
}