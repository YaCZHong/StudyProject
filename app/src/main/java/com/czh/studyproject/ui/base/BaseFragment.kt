package com.czh.studyproject.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
* @Description: Fragment基类，（注意：子类在继承该类时，不可将子类构造器设置为私有，即 private constructor()，否则在 fragment 恢复时会找不到构造方法导致重建失败）
 * @Author: czh
* @CreateDate: 2022/4/5 0:46
*/
abstract class BaseFragment<T : ViewBinding>(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    private var _binding: T? = null
    val binding: T
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = initBinding(view)
        init(savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    abstract fun initBinding(view: View): T
    abstract fun init(savedInstanceState: Bundle?)
}