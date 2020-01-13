package com.makemoneyonline.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.makemoneyonline.R
import com.makemoneyonline.ui.adapter.DashBoardAdapter
import kotlinx.android.synthetic.main.dashboard_fragemnt.*

class DashBoardActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_fragemnt)
        setUpElemets()


    }

    private fun setUplisteners() {
        constraintLayout.setOnClickListener {
            val intent = Intent(this@DashBoardActivity, DashBoardDetailActvity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpElemets() {
        val mLayoutManager =
            LinearLayoutManager(this@DashBoardActivity)
        recyclerViewDashBoard.layoutManager = mLayoutManager
        recyclerViewDashBoard.adapter =
            DashBoardAdapter(this)
        setUplisteners()
    }


}