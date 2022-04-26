package com.czh.studyproject.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import com.czh.studyproject.databinding.ActivityDynamicVpBinding
import com.czh.studyproject.ui.adapter.vp.DynamicVpAdapter
import com.czh.studyproject.ui.base.BaseActivity
import com.czh.studyproject.ui.fragment.TextFragment

class DynamicVpActivity : BaseActivity<ActivityDynamicVpBinding>() {

    private val mAdapter = DynamicVpAdapter(this)

    private var num = 0

    override fun initBinding(layoutInflater: LayoutInflater): ActivityDynamicVpBinding {
        return ActivityDynamicVpBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {
        binding.apply {
            btnAddFragment.setOnClickListener {
                mAdapter.addFragment(TextFragment.newInstance("${num++}"))
            }
            btnReduceFragment.setOnClickListener {
                mAdapter.removeFragment(vpDynamic.currentItem)
            }
            vpDynamic.adapter = mAdapter
            indicator.bindVp(vpDynamic)
        }
    }
}