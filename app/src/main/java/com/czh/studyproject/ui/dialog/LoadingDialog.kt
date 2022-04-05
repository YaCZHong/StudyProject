package com.czh.studyproject.ui.dialog

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment
import com.czh.studyproject.R

class LoadingDialog(
    private val msg: String? = null,
    private val cancelable: Boolean = true
) : DialogFragment(R.layout.dialog_loading) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.LoadingDialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<AppCompatTextView>(R.id.tv_loading_hint)
        if (TextUtils.isEmpty(msg)) {
            textView.visibility = View.GONE
        } else {
            textView.text = msg
        }
        if (!cancelable) {
            isCancelable = false
        }
    }
}