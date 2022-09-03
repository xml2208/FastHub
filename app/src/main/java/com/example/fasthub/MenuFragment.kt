package com.example.fasthub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import com.example.fasthub.databinding.FragmentSimpleBinding
import com.example.fasthub.ui.mainActivity.MainActivity
import com.example.fasthub.ui.mainActivity.drawer_main.*
import com.example.fasthub.ui.mainActivity.drawer_main.gists.GistsFragment
import com.example.fasthub.ui.mainActivity.drawer_main.pinned.PinnedFragment
import com.example.fasthub.ui.mainActivity.drawer_main.profile.ProfileFragment
import com.google.android.material.navigation.NavigationView

class MenuFragment : Fragment() {
    private var _vb: FragmentSimpleBinding? = null
    private val vb get() = _vb!!

    companion object {
        private val homeFr = HomeFragment()
        private val profileFragment = ProfileFragment()
        private val pinnedFragment = PinnedFragment()
        private val trendingFragment = TrendingFragment()
        private val gistsFragment = GistsFragment()
        private val faqFragment = FAQFragment()
    }

    private lateinit var navigationView: NavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentSimpleBinding.inflate(layoutInflater)
        val navView = vb.simpleNav1
        navigationView = navView
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> setCurrentFragment(homeFr)
                R.id.profileFragment -> setCurrentFragment(profileFragment)
                R.id.pinnedFragment -> setCurrentFragment(pinnedFragment)
                R.id.trendingFragment -> setCurrentFragment(trendingFragment)
                R.id.gistsFragment -> setCurrentFragment(gistsFragment)
                R.id.FAQFragment -> setCurrentFragment(faqFragment)
                else -> setCurrentFragment(homeFr)
            }
            return@OnNavigationItemSelectedListener true
        })
    }

    private fun setCurrentFragment(fragment: Fragment) {
        (activity as MainActivity)
            .supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
        (activity as MainActivity).drawer.closeDrawer(GravityCompat.START)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }
}