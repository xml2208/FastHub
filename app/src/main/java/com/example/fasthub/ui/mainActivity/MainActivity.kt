package com.example.fasthub.ui.mainActivity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.fasthub.R
import com.example.fasthub.MenuFragment
import com.example.fasthub.SimpleFragment2
import com.example.fasthub.databinding.ActivityMainBinding
import com.example.fasthub.ui.mainActivity.adapters.ViewPagerAdapter
import com.example.fasthub.ui.mainActivity.drawer_main.*
import com.example.fasthub.ui.mainActivity.drawer_main.profile.ProfileViewModel
import com.example.fasthub.ui.mainActivity.search.SearchActivity
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMainBinding
    lateinit var drawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private val vm: ProfileViewModel by viewModels()
    private val homeFr = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
        setSupportActionBar(vb.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        drawer = vb.drawableLayout
        toolbar = vb.toolbar
        navigationView = vb.navView
        tabLayout = vb.tabLayoutDrawer
        viewPager = vb.viewPagerDrawer

        viewPager.apply {
            adapter = ViewPagerAdapter(listOf(MenuFragment(), SimpleFragment2()), supportFragmentManager, lifecycle)
        }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = title(position)
        }.attach()

        val header = navigationView.getHeaderView(0)
        val headerImage = header.findViewById<ImageView>(R.id.imgView)
        val headerUserName = header.findViewById<TextView>(R.id.username_header)
        val headerName = header.findViewById<TextView>(R.id.name_header)

        setCurrentFragment(homeFr)
        toggle = ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        toggle.drawerArrowDrawable.color = Color.BLACK

        lifecycleScope.launchWhenResumed {
            vm.img.collect { img ->
                headerImage?.load(img)
            }
        }
        lifecycleScope.launchWhenResumed {
            vm.userName.collect { userName ->
                headerUserName?.text = userName
            }
        }
        lifecycleScope.launchWhenResumed {
            vm.profileName.collect {
                headerName?.text = it
            }
        }
    }

    override fun onBackPressed() {
        setCurrentFragment(homeFr)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        }
        super.onBackPressed()
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        drawer.closeDrawer(GravityCompat.START)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        if (item.itemId == R.id.search) {
            startActivity(Intent(this, SearchActivity::class.java))
        }
        if (item.itemId == android.R.id.home) {
            Toast.makeText(this, "Back button pressed", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

//  fun setToolBar(title: String?) {
//        supportActionBar!!.title = title
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
//    }

    private fun title(position: Int): String {
        return when (position) {
            0 -> "MENU"
            1 -> "PROFILE"
            else -> "NULL"
        }
    }
}
