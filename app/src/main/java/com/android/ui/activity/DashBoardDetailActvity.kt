package com.android.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.vaibhavi.android.R
import kotlinx.android.synthetic.main.dashboard_layout_detail.*
import kotlinx.android.synthetic.main.toolbar.*


class DashBoardDetailActvity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_layout_detail)
        setSupportActionBar(custom_toolbar)
        val intent = intent
        val id =intent.getStringExtra("id")
        webviewDashBoardDetail.loadUrl("https://demo.adsandurl.com/money-making/get-detail.php?id=$id");
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


}