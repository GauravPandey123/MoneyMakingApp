package com.makemoneyonline.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makemoneyonline.R
import com.makemoneyonline.model.dashboard.ServicesItem

class DashBoardAdapter(val context: Context?, val dashBoardArrayList: ArrayList<ServicesItem>) :
    RecyclerView.Adapter<DashBoardAdapter.DashBoardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.dashboard_item, parent, false)
        return DashBoardViewHolder(view)
    }

    override fun getItemCount(): Int = dashBoardArrayList.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        val dashBoardArrayListData = dashBoardArrayList[position]
        Glide.with(context!!).load(dashBoardArrayList[position].image).into(holder.imageView)
        holder.textViewDashBoardTitle.text = dashBoardArrayList[position].title
        holder.textViewDescription.text = "Way No " + dashBoardArrayListData.orderNo
    }

    inner class DashBoardViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewDashBoardItem)
        val textViewDashBoardTitle: TextView = view.findViewById(R.id.textViewDashBoardTitle)
        val textViewDescription: TextView = view.findViewById(R.id.textViewDescription)
    }

}