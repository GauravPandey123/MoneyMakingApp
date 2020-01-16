package com.android.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.android.ui.fragment.DashBoardFragment
import com.android.ui.fragment.DrawerFragment
import com.vaibhavi.android.R
import com.vaibhavi.android.changeFragmentForBottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity(),DrawerFragment.FragmentDrawerListener{

    private lateinit var drawerFragment: DrawerFragment


    private val fragmentManager: FragmentManager by lazy {
        supportFragmentManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(custom_toolbar)
        custom_toolbar.setTitleTextColor(Color.WHITE)
        custom_toolbar.title = getString(R.string.dashboard)
        drawerFragment = supportFragmentManager.findFragmentById(R.id.fragment_navigation_drawer) as DrawerFragment
        drawerFragment.init(R.id.fragment_navigation_drawer, drawer_layout, custom_toolbar)
        displayView(0)
        setUpListeners()

    }

    private fun setUpListeners() {

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.menu_share ->
                shareTextUrl()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareTextUrl(): Boolean {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
        }
        share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post")
        share.putExtra(Intent.EXTRA_TEXT, "http://www.codeofaninja.com")
        startActivity(Intent.createChooser(share, "Share link!"))

        return true
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


     fun displayView(position: Int) {
        var title = getString(R.string.app_name)
        when (position) {
            0 -> {
                fragmentManager.changeFragmentForBottomNavigationView(
                    R.id.main_content,
                    DashBoardFragment()
                )
            }

            else -> {
            }
        }

//        if (fragment != null) {
//            val fragmentManager = supportFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.main_content, fragment)
//            fragmentTransaction.commit()
//            supportActionBar?.title = title
//        }
    }

    override fun onDrawerItemSelected(view: View, position: Int) {
        displayView(position)

    }
}
