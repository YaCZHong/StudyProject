package com.czh.studyproject.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.czh.studyproject.R
import com.czh.studyproject.databinding.DialogFragmentFullScreenBinding
import com.czh.studyproject.ui.base.BaseDialogFragment

class FullScreenDialog :
    BaseDialogFragment<DialogFragmentFullScreenBinding>(R.layout.dialog_fragment_full_screen) {

    companion object {
        fun newInstance(): FullScreenDialog {
            return FullScreenDialog()
        }
    }

    override fun initBinding(view: View): DialogFragmentFullScreenBinding {
        return DialogFragmentFullScreenBinding.bind(view)
    }

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun onStart() {
        super.onStart()
        dialog!!.window!!.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            }
        }
    }
}