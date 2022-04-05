package com.czh.studyproject.ui.adapter.vp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @Description: viewPager2中fragment有涉及到动态添加移除时使用该adapter
 * @Author: czh
 * @CreateDate: 2022/4/5 20:43
 */
class DynamicVpAdapter : FragmentStateAdapter {

    constructor(fragmentActivity: FragmentActivity) : super(fragmentActivity)
    constructor(fragment: Fragment) : super(fragment)

    private val fragments: MutableList<Fragment> = mutableListOf()
    private val fragmentIds: List<Long>
        get() = fragments.map {
            it.hashCode().toLong()
        }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun containsItem(itemId: Long): Boolean {
        return fragmentIds.contains(itemId)
    }

    override fun getItemId(position: Int): Long {
        return fragmentIds[position]
    }

    fun setFragments(fragments: List<Fragment>) {
        this.fragments.clear()
        this.fragments.addAll(fragments)
        notifyDataSetChanged()
    }
}