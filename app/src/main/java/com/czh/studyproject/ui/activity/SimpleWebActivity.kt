package com.czh.studyproject.ui.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import com.czh.studyproject.databinding.ActivitySimpleWebBinding
import com.czh.studyproject.ui.base.BaseActivity

class SimpleWebActivity : BaseActivity<ActivitySimpleWebBinding>() {

    private var webView: WebView? = null

    override fun initBinding(layoutInflater: LayoutInflater): ActivitySimpleWebBinding {
        return ActivitySimpleWebBinding.inflate(layoutInflater)
    }

    override fun init(savedInstanceState: Bundle?) {
        initWebView()
    }

    private fun initWebView() {
        WebView(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    showLoading("载入中...")
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    hideLoading()
                }
            }
            settings.apply {
                useWideViewPort = true
                loadWithOverviewMode = true
                javaScriptEnabled = true
                domStorageEnabled = true
                javaScriptCanOpenWindowsAutomatically = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
            webView = this
            binding.container.addView(this)
            loadUrl("https://xiaozhuanlan.com/kunminx")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        webView?.also {
            it.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            it.clearCache(true)
            it.clearHistory()
            (it.parent as ViewGroup).removeView(it)
            it.destroy()
        }
        webView = null
    }
}