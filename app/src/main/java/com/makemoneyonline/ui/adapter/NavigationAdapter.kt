package com.makemoneyonline.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makemoneyonline.R
import com.makemoneyonline.ui.model.DrawerItem

class NavigationAdapter(val context: Context, val arrayListItem: ArrayList<DrawerItem>) :
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
        val arrayList = arrayListItem[position]
        holder.navigationTextView.text = arrayListItem[position].title

    }


    inner class NavigationViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageViewDrawer = view.findViewById<ImageView>(R.id.imageViewNavigationDrawer)
        val navigationTextView = view.findViewById<TextView>(R.id.navigationTextView)
    }


}