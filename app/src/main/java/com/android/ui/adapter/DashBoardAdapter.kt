package com.android.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.android.enum.DashBoardEnum
import com.bumptech.glide.Glide
import com.android.model.dashboard.ServicesItem
import com.vaibhavi.android.R

class DashBoardAdapter(val context: Context?, val dashBoardArrayList: ArrayList<ServicesItem>,
                       val listener: (String, Any) -> Unit) :
    RecyclerView.Adapter<DashBoardAdapter.DashBoardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.dashboard_item, parent, false)
        return DashBoardViewHolder(view)
    }

    override fun getItemCount(): Int = dashBoardArrayList.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {
        val dashBoardArrayListData = dashBoardArrayList[position]
        Glide.with(context!!).load(dashBoardArrayList[position].image).error(R.drawable.ic_image_place_holder).placeholder(R.drawable.ic_image_place_holder).into(holder.imageView)
        holder.textViewDashBoardTitle.text = dashBoardArrayList[position].title
        holder.textViewDescription.text = "Way No " + dashBoardArrayListData.wayNo
        holder.constraintLayoutRoot.setOnClickListener {
            listener(DashBoardEnum.HomeEvent.event,dashBoardArrayList[position])
        }
    }

    inner class DashBoardViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageViewDashBoardItem)
        val textViewDashBoardTitle: TextView = view.findViewById(R.id.textViewDashBoardTitle)
        val textViewDescription: TextView = view.findViewById(R.id.textViewDescription)
        val constraintLayoutRoot : ConstraintLayout = view.findViewById(R.id.constraintLayoutRoot)
    }

}