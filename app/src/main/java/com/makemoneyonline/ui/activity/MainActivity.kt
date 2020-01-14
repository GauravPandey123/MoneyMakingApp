package com.makemoneyonline.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.makemoneyonline.R
import com.makemoneyonline.ui.fragment.DashBoardFragment
import com.makemoneyonline.ui.fragment.DrawerFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(),DrawerFragment.FragmentDrawerListener{

    private lateinit var drawerFragment: DrawerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(custom_toolbar)
        drawerFragment = supportFragmentManager.findFragmentById(R.id.fragment_navigation_drawer) as DrawerFragment
        drawerFragment.init(R.id.fragment_navigation_drawer, drawer_layout, custom_toolbar)
        displayView(0)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }


    private fun displayView(position: Int) {
        var fragment: Fragment? = null
        var title = getString(R.string.app_name)
        when (position) {
            0 -> {
                fragment = DashBoardFragment()
                title = getString(R.string.dashboard)
            }

            else -> {
            }
        }

        if (fragment != null) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main_content, fragment)
            fragmentTransaction.commit()
            supportActionBar?.title = title
        }
    }

    override fun onDrawerItemSelected(view: View, position: Int) {
        displayView(position)

    }
}
