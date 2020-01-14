package com.makemoneyonline.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
        // Add data to the intent, the receiving app will decide
// what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post")
        share.putExtra(Intent.EXTRA_TEXT, "http://www.codeofaninja.com")
        startActivity(Intent.createChooser(share, "Share link!"))

        return true
    }

//    private fun shareTextUrl() {
//
//    }

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
