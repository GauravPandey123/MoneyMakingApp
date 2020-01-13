package com.makemoneyonline.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makemoneyonline.R

class DashBoardAdapter(val context: Context) :
    RecyclerView.Adapter<DashBoardAdapter.DashBoardViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.dashboard_item, parent, false)
        return DashBoardViewHolder(view)
    }

    override fun getItemCount(): Int = 10


    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
    }

    inner class DashBoardViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.imageViewDashBoardItem)
        val textViewDashBoardTitle = view.findViewById<TextView>(R.id.textViewDashBoardTitle)
        val textViewDescription = view.findViewById<TextView>(R.id.textViewDescription)
    }

}