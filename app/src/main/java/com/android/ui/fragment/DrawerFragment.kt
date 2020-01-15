package com.android.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.enum.DashBoardEnum
import com.android.model.dashboard.DashBaordResponse
import com.android.model.dashboard.ServicesItem
import com.android.ui.activity.DashBoardDetailActvity
import com.android.ui.activity.MainActivity
import com.android.ui.activity.PrivacyPolicyActivity
import com.android.ui.adapter.NavigationAdapter
import com.android.ui.viewmodel.DashBoardViewModel
import com.vaibhavi.android.R
import kotlinx.android.synthetic.main.drawer_footer_layout.*
import kotlinx.android.synthetic.main.drawer_header_layout.*
import kotlinx.android.synthetic.main.navigation_drawer_fragment.*
import kotlin.collections.ArrayList


class DrawerFragment : Fragment() {

    private lateinit var adapter: NavigationAdapter

    private var drawerListener: FragmentDrawerListener? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var containerView: View? = null
    private lateinit var dashBoardArrayList: ArrayList<ServicesItem>

    private val dashBoardViewModel: DashBoardViewModel by lazy {
        ViewModelProviders.of(this).get(DashBoardViewModel::class.java)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            drawerListener = context as FragmentDrawerListener
        } catch (e: RuntimeException) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.navigation_drawer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashBoardArrayList = ArrayList()
        setUpWebService()
        textViewHome.setOnClickListener{
        }

        textViewPrivacyPolicy.setOnClickListener {
            val intent = Intent(activity, PrivacyPolicyActivity::class.java)
            startActivity(intent)
            activity?.overridePendingTransition(R.anim.enter, R.anim.exit)
        }

    }



    private fun setUpWebService() {
        dashBoardViewModel.dashBoarddata().observe(this, androidx.lifecycle.Observer {
            val dashBaordResponse = it.response as DashBaordResponse
            if (dashBaordResponse.success) {
                getDashBoardDataResponse(dashBaordResponse.services)
            } else {

            }

        })
    }

    private fun getDashBoardDataResponse(services: ArrayList<ServicesItem>) {
        dashBoardArrayList.addAll(services)
        val mLayoutManager =
            LinearLayoutManager(context)
        listDrawer.layoutManager = mLayoutManager
        listDrawer.adapter =
            NavigationAdapter(context, dashBoardArrayList) { event, ServicesItem ->
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

    private fun sendtonextActvity(id: String?,title: String ?) {
        val intent = Intent(activity, DashBoardDetailActvity::class.java)
        intent.putExtra("id", id)
        intent.putExtra("title",title)
        startActivity(intent)
        mDrawerLayout?.closeDrawer(containerView!!)
        activity?.overridePendingTransition(R.anim.enter, R.anim.exit)

    }

    fun init(fragmentId: Int, drawerLayout: DrawerLayout, toolbar: Toolbar) {
        containerView = activity?.findViewById(fragmentId)
        mDrawerLayout = drawerLayout

        toolbar.post {
            val d =
                ResourcesCompat.getDrawable(resources, R.drawable.ic_money, null)
            toolbar.navigationIcon = d
        }
        val drawerToggle = object : ActionBarDrawerToggle(
            activity,
            drawerLayout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        ) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                activity?.invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                activity?.invalidateOptionsMenu()
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                toolbar.alpha = 1 - slideOffset / 2
            }
        }

        mDrawerLayout?.addDrawerListener(drawerToggle)
        mDrawerLayout?.post { drawerToggle.syncState() }

    }

    interface ClickListener {
        fun onClick(view: View, position: Int)
        fun onLongClick(view: View?, position: Int)
    }

    internal class RecyclerTouchListener(
        context: Context,
        recyclerView: RecyclerView,
        private val clickListener: ClickListener?
    ) : RecyclerView.OnItemTouchListener {

        private val gestureDetector: GestureDetector

        init {
            gestureDetector =
                GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                    override fun onSingleTapUp(e: MotionEvent): Boolean {
                        return true
                    }

                    override fun onLongPress(e: MotionEvent) {
                        val child = recyclerView.findChildViewUnder(e.x, e.y)
                        if (child != null && clickListener != null) {
                            clickListener.onLongClick(
                                child,
                                recyclerView.getChildAdapterPosition(child)
                            )
                        }
                    }
                })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }

    interface FragmentDrawerListener {
        fun onDrawerItemSelected(view: View, position: Int)
    }
}
