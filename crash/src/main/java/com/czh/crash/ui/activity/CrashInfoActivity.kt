package com.czh.crash.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.czh.crash.R
import com.czh.crash.ui.util.ParseTimeUtil
import com.czh.crash.vm.CrashVM

internal class CrashInfoActivity : CrashBaseActivity() {
    companion object {
        private const val UID = "uid"

        fun actionStart(context: Context, uid: Int) {
            Intent(context, CrashInfoActivity::class.java).apply {
                putExtra(UID, uid)
                context.startActivity(this)
            }
        }
    }

    override val activityTitle: String
        get() = "CRASH详情"

    private var uid: Int = -1
    private val vm by viewModels<CrashVM>()

    private lateinit var tvCrashTime: TextView
    private lateinit var tvUserId: TextView
    private lateinit var tvAppVersionName: TextView
    private lateinit var tvAppVersionCode: TextView
    private lateinit var tvDeviceBrand: TextView
    private lateinit var tvDeviceModel: TextView
    private lateinit var tvOsVersion: TextView
    private lateinit var tvThreadName: TextView
    private lateinit var tvThrowableSimple: TextView
    private lateinit var tvThrowableDetail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crash_info)
        init()
    }

    private fun init() {
        initIntent()
        initUI()
        initData()
    }

    private fun initIntent() {
        uid = intent.getIntExtra(UID, -1)
        if (uid == -1) {
            Toast.makeText(this, "无效的uid值！", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }
    }

    private fun initUI() {
        tvCrashTime = findViewById(R.id.tv_crash_time)
        tvUserId = findViewById(R.id.tv_user_id)
        tvAppVersionName = findViewById(R.id.tv_app_version_name)
        tvAppVersionCode = findViewById(R.id.tv_app_version_code)
        tvDeviceBrand = findViewById(R.id.tv_device_brand)
        tvDeviceModel = findViewById(R.id.tv_device_model)
        tvOsVersion = findViewById(R.id.tv_os_version)
        tvThreadName = findViewById(R.id.tv_thread_name)
        tvThrowableSimple = findViewById(R.id.tv_throwable_simple)
        tvThrowableDetail = findViewById(R.id.tv_throwable_detail)
    }

    private fun initData() {
        vm.readCrashFromDB(uid).observe(this) { crash ->
            crash?.let {
                tvCrashTime.text = ParseTimeUtil.parseTime(it.timestamp)
                tvUserId.text = if (TextUtils.isEmpty(it.userId)) "未设置用户ID" else it.userId
                tvAppVersionName.text = it.versionName
                tvAppVersionCode.text = it.versionCode.toString()
                tvDeviceBrand.text = it.deviceBrand
                tvDeviceModel.text = it.deviceModel
                tvOsVersion.text = it.osVersion
                tvThreadName.text = it.thread
                tvThrowableSimple.text = it.throwableSimple
                tvThrowableDetail.text = it.throwableDetail
            }
        }
    }
}