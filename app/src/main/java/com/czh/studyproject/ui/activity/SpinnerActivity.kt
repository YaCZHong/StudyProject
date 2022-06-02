package com.czh.studyproject.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.czh.studyproject.R
import com.czh.studyproject.databinding.ActivitySpinnerBinding
import com.czh.studyproject.ui.base.BaseActivity
import com.czh.xhlib.toast.toast

class SpinnerActivity : BaseActivity<ActivitySpinnerBinding>() {

    private val data = listOf<String>("1", "2", "3", "4", "5", "6", "7")

    override fun initBinding(layoutInflater: LayoutInflater): ActivitySpinnerBinding {
        return ActivitySpinnerBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {
        initUI()
    }

    private fun initUI() {
        binding.apply {
            spinner.adapter =
                ArrayAdapter(this@SpinnerActivity, R.layout.spinner_item_select, data).apply {
                    setDropDownViewResource(R.layout.spinner_item_unselect)
                }
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    toast(data[position])
                    Log.d(TAG,data[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
//            spinner.setSelection(6)
        }
    }

    companion object{
        private const val TAG = "SpinnerActivity"
    }
}