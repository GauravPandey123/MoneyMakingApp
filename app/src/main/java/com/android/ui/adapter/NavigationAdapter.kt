package com.android.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.enum.DashBoardEnum
import com.android.model.dashboard.ServicesItem
import com.bumptech.glide.Glide
import com.vaibhavi.android.R

class NavigationAdapter(
    val context: Context?,
    val arrayListItem: ArrayList<ServicesItem>,
    val listener: (String, Any) -> Unit
) :
    RecyclerView.Adapter<NavigationAdapter.NavigationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.navigationdrawer_item, parent, false)
        return NavigationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayListItem.size
    }

    override fun onBindViewHolder(holder: NavigationViewHolder, position: Int) {
        holder.navigationTextView.text = arrayListItem[position].title
        Glide.with(context!!).load(arrayListItem[position].iconimage)
            .into(holder.imageViewDrawer)
        holder.relativeLayoutNavigation.setOnClickListener {
            listener(DashBoardEnum.HomeEvent.event,arrayListItem[position])

        }

    }


    inner class NavigationViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageViewDrawer = view.findViewById<ImageView>(R.id.imageViewNavigationDrawer)
        val navigationTextView = view.findViewById<TextView>(R.id.navigationTextView)
        val relativeLayoutNavigation = view.findViewById<RelativeLayout>(R.id.relativeLayoutNavigation)

    }


}