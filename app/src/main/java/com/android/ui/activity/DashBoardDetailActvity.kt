package com.android.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.vaibhavi.android.R
import kotlinx.android.synthetic.main.dashboard_layout_detail.*
import kotlinx.android.synthetic.main.main_toolbar.*
import kotlinx.android.synthetic.main.toolbar.*


class DashBoardDetailActvity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_layout_detail)
        val intent = intent
        val id = intent.getStringExtra("id")
        val title = intent.getStringExtra("title")
        textViewTitle.text = title
        webviewDashBoardDetail.loadUrl("https://demo.adsandurl.com/money-making/get-detail.php?id=$id");


        setUpListeners()
    }

    private fun setUpListeners() {
        imageviewbacktoolbar.setOnClickListener {
            overridePendingTransition(R.anim.enter, R.anim.exit)
            finish()
        }
    }


}