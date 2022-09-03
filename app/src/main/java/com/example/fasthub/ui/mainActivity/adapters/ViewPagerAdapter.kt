package com.example.fasthub.ui.mainActivity.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(list: List<Fragment>, fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    private val frList = list

    override fun getItemCount() = frList.size

    override fun createFragment(position: Int): Fragment {
        return frList[position]
    }
}