package com.android.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.enum.DashBoardEnum
import com.android.model.dashboard.DashBaordResponse
import com.android.model.dashboard.ServicesItem
import com.android.ui.activity.DashBoardDetailActvity
import com.android.ui.adapter.DashBoardAdapter
import com.android.ui.viewmodel.DashBoardViewModel
import com.vaibhavi.android.R
import kotlinx.android.synthetic.main.dashboard_fragemnt.*

class DashBoardFragment : Fragment() {

    private lateinit var dashBoardArrayList: ArrayList<ServicesItem>


    private val dashBoardViewModel: DashBoardViewModel by lazy {
        ViewModelProviders.of(this).get(DashBoardViewModel::class.java)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboard_fragemnt, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpElements()
    }

    private fun setUpElements() {
        dashBoardArrayList = ArrayList()
        setUpWebService()

    }

    private fun setUpWebService() {
        dashBoardViewModel.dashBoarddata().observe(this, Observer {
            val dashBaordResponse = it.response as DashBaordResponse
            if (dashBaordResponse.success) {
                contatinProgresBarDashBoardData.visibility = View.GONE
                getDashBoardDataResponse(dashBaordResponse.services)
            } else {
                contatinProgresBarDashBoardData.visibility = View.GONE

            }
        })
    }

    private fun getDashBoardDataResponse(services: ArrayList<ServicesItem>) {
        dashBoardArrayList.addAll(services)
        val mLayoutManager =
            LinearLayoutManager(context)
        recyclerViewDashBoard.layoutManager = mLayoutManager
        recyclerViewDashBoard.adapter =
            DashBoardAdapter(context, dashBoardArrayList) { event, ServicesItem ->
                handleProductListEvents(event, ServicesItem)

            }

    }

    private fun handleProductListEvents(event: String, servicesItem: Any) {
        if (servicesItem is ServicesItem) {
            when (event) {
                DashBoardEnum.HomeEvent.event ->
                    sendtonextActvity(servicesItem.id,servicesItem.title)

            }
        }
    }

    private fun sendtonextActvity(id: String?,title :String?) {
        val intent = Intent(activity, DashBoardDetailActvity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("title",title)
        startActivity(intent)
        activity?.overridePendingTransition(R.anim.enter, R.anim.exit)

    }




}