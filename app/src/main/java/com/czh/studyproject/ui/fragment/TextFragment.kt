package com.czh.studyproject.ui.fragment

import android.os.Bundle
import android.view.View
import com.czh.studyproject.R
import com.czh.studyproject.databinding.FragmentTextBinding
import com.czh.studyproject.ui.base.BaseFragment

class TextFragment private constructor() :
    BaseFragment<FragmentTextBinding>(R.layout.fragment_text) {

    override fun initBinding(view: View): FragmentTextBinding {
        return FragmentTextBinding.bind(view)
    }

    override fun init(savedInstanceState: Bundle?) {
        binding.tvText.text = requireArguments().getString(TEXT, "")
    }

    companion object {
        private const val TEXT = "text"

        fun newInstance(text: String): TextFragment {
            return TextFragment().apply {
                arguments = Bundle().apply {
                    putString(TEXT, text)
                }
            }
        }
    }
}