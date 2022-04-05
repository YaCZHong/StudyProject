package com.czh.studyproject.ui.adapter.vp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @Description: viewPager2中fragment不涉及到动态添加移除时使用该adapter
 * @Author: czh
 * @CreateDate: 2022/4/5 20:43
 */
class StaticVpAdapter : FragmentStateAdapter {

    private var fragments: List<Fragment>

    constructor(fragmentActivity: FragmentActivity, fragments: List<Fragment>) : super(
        fragmentActivity
    ) {
        this.fragments = fragments
    }

    constructor(fragment: Fragment, fragments: List<Fragment>) : super(fragment) {
        this.fragments = fragments
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}