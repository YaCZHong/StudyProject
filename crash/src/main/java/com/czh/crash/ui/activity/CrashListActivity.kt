package com.czh.crash.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.czh.crash.R
import com.czh.crash.ui.adapter.CrashAdapter
import com.czh.crash.ui.decoration.MyItemDecoration
import com.czh.crash.vm.CrashVM

class CrashListActivity : CrashBaseActivity() {

    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, CrashListActivity::class.java))
        }
    }

    override val activityTitle: String
        get() = "CRASH列表"

    private val vm by viewModels<CrashVM>()

    private lateinit var mAdapter: CrashAdapter
    private lateinit var rvCrash: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crash_list)
        init()
    }

    private fun init() {
        initUI()
        initData()
    }

    private fun initUI() {
        rvCrash = findViewById(R.id.rv_crash)
        rvCrash.addItemDecoration(MyItemDecoration())
    }

    private fun initData() {
        vm.readAllCrashFromDB().observe(this) { list ->
            list?.let {
                mAdapter = CrashAdapter(it) { crash ->
                    CrashInfoActivity.actionStart(this, crash.uid)
                }
                rvCrash.adapter = mAdapter
            }
        }
    }
}