package com.czh.crash.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.czh.crash.CrashHandler
import com.czh.crash.R
import com.czh.crash.ui.adapter.CrashAdapter
import com.czh.crash.ui.decoration.MyItemDecoration

class CrashListActivity : CrashBaseActivity() {

    companion object {
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, CrashListActivity::class.java))
        }
    }

    override val activityTitle: String
        get() = "CRASH列表"

    private lateinit var mAdapter: CrashAdapter
    private lateinit var rvCrash: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crash_list)
        init()
    }

    private fun init() {
        rvCrash = findViewById(R.id.rv_crash)
        rvCrash.addItemDecoration(MyItemDecoration())
        CrashHandler.readAllCrashFromDB { list ->
            mAdapter = CrashAdapter(list) { crash ->
                CrashInfoActivity.actionStart(this, crash.uid)
            }
            rvCrash.adapter = mAdapter
        }
    }
}