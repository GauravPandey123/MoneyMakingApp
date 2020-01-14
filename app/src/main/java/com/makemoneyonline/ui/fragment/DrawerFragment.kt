package com.makemoneyonline.ui.fragment

import android.content.Context
import android.os.Bundle

import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.makemoneyonline.R
import com.makemoneyonline.ui.adapter.NavigationAdapter
import com.makemoneyonline.ui.model.DrawerItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_drawer_fragment.*

import java.util.ArrayList

class DrawerFragment : Fragment() {

    private lateinit var adapter: NavigationAdapter

    private var drawerListener: FragmentDrawerListener? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var containerView: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            drawerListener = context as FragmentDrawerListener
        } catch (e : RuntimeException) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.navigation_drawer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titles = activity?.resources?.getStringArray(R.array.nav_drawer_labels)
        val data = ArrayList<DrawerItem>()
        for (i in titles!!.indices) {
            val navItem = DrawerItem(title = titles[i])
            data.add(navItem)
        }

        adapter = NavigationAdapter(context!!,data)
        listDrawer.adapter = adapter
        listDrawer.layoutManager = LinearLayoutManager(activity)
        listDrawer.addOnItemTouchListener(RecyclerTouchListener(activity!!, listDrawer, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                drawerListener?.onDrawerItemSelected(view, position)
                mDrawerLayout?.closeDrawer(containerView!!)
            }

            override fun onLongClick(view: View?, position: Int) {

            }
        }))
    }

    fun init(fragmentId: Int, drawerLayout: DrawerLayout, toolbar: Toolbar) {
        containerView = activity?.findViewById(fragmentId)
        mDrawerLayout = drawerLayout
        val drawerToggle = object : ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
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

    internal class RecyclerTouchListener(context: Context, recyclerView: RecyclerView, private val clickListener: ClickListener?) : RecyclerView.OnItemTouchListener {

        private val gestureDetector: GestureDetector

        init {
            gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return true
                }
                override fun onLongPress(e: MotionEvent) {
                    val child = recyclerView.findChildViewUnder(e.x, e.y)
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child))
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
