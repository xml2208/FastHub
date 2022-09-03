package com.example.fasthub.ui.mainActivity.drawer_main.gists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.fasthub.databinding.FragmentGistsBinding
import com.example.fasthub.ui.mainActivity.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GistsFragment : Fragment() {
    private var _vb: FragmentGistsBinding? = null
    private val vb get() = _vb!!
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private val myGistsFr = UserGistsFragment.newInstance()
    private val starredGistsFr = StarredGistsFragment.newInstance()
    private val publicGistsFr = PublicGistsFragment.newInstance()
    private val frList = listOf(myGistsFr, starredGistsFr, publicGistsFr)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentGistsBinding.inflate(layoutInflater)
        tabLayout = vb.tabLayout
        viewPager = vb.viewPager
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = ViewPagerAdapter(frList, childFragmentManager, lifecycle)
        viewPager.isSaveEnabled = false

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = setTitle(position)
        }.attach()
    }

    private fun setTitle(position: Int): String {
        return when (position) {
            0 -> "My Gists"
            1 -> "Starred"
            2 -> "Public Gists"
            else -> {
                "NULL"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }
}