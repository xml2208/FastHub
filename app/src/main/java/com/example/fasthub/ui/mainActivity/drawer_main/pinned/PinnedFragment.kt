package com.example.fasthub.ui.mainActivity.drawer_main.pinned

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fasthub.databinding.FragmentPinnedBinding
import com.example.fasthub.ui.mainActivity.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PinnedFragment : Fragment() {
    private var _vb: FragmentPinnedBinding? = null
    private val vb get() = _vb!!
    private lateinit var tabLayout: TabLayout
    private val frList = listOf(
        PinnedRepoFragment.newInstance(),
        PinnedGistsFragment.newInstance(),
        PinnedIssueFragment.newInstance()
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentPinnedBinding.inflate(layoutInflater)

        tabLayout = vb.tabLayoutPinned
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE

        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vb.viewPagerPinned.apply {
            adapter = ViewPagerAdapter(frList, childFragmentManager, lifecycle)
            isSaveEnabled = false
        }
        TabLayoutMediator(tabLayout, vb.viewPagerPinned) { tab, position ->
            tab.text = setTitle(position)
        }.attach()
    }

    private fun setTitle(position: Int): String {
        return when (position) {
            0 -> "REPOSITORIES"
            1 -> "ISSUES"
            2 -> "PULL REQUESTS"
            3 -> "GISTS"
            else -> {
                "null"
            }
        }
    }

    companion object {
        fun newInstance(): PinnedFragment {
            return PinnedFragment()
        }
    }
}