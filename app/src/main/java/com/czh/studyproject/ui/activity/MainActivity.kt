package com.czh.studyproject.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.czh.studyproject.R
import com.czh.studyproject.ui.adapter.vp.DynamicVpAdapter

class MainActivity : AppCompatActivity() {

    private val mAdapter = DynamicVpAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}