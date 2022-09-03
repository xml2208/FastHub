package com.example.fasthub.ui.mainActivity.drawer_main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fasthub.ui.mainActivity.bottomNav.FeedsFragment
import com.example.fasthub.databinding.FragmentProfileBinding
import com.example.fasthub.ui.mainActivity.adapters.ViewPagerAdapter
import com.example.fasthub.ui.mainActivity.drawer_main.gists.UserGistsFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayout


class ProfileFragment : Fragment() {
    private var _vb: FragmentProfileBinding? = null
    private val vb get() = _vb!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentProfileBinding.inflate(layoutInflater)
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabLayout = vb.tabLayout
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE


        val myAdapter = ViewPagerAdapter(list, childFragmentManager, lifecycle)
        vb.viewPager.adapter = myAdapter
        vb.viewPager.isSaveEnabled = false

        TabLayoutMediator(
            tabLayout, vb.viewPager
        ) { tab, position -> tab.text = title(position) }.attach()
    }

    private fun title(position: Int): String {
        return when (position) {
            0 -> "OVERVIEW"
            1 -> "FEED"
            2 -> "REPOSITORIES"
            3 -> "STARRED()"
            4 -> "GISTS"
            5 -> "FOLLOWERS"
            6 -> "FOLLOWING"
            else -> "NULL"
        }
    }
    companion object {
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
        val list = listOf(
            ProfileOverview(),
            FeedsFragment(),
            ProfileRepositoriesFragment(),
            StarredReposFragment(),
            UserGistsFragment(),
            FollowersFragment(),
            FollowingFragment()
        )
    }
}