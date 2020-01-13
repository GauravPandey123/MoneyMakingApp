package com.makemoneyonline.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.makemoneyonline.R
import kotlinx.android.synthetic.main.dashboard_layout_detail.*

class DashBoardDetailActvity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_layout_detail)
        webviewDashBoardDetail.settings.javaScriptEnabled = true;
        webviewDashBoardDetail.loadUrl("http://www.google.com");

    }
}