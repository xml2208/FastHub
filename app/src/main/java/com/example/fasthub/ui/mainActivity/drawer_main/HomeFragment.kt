package com.example.fasthub.ui.mainActivity.drawer_main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fasthub.ui.mainActivity.bottomNav.FeedsFragment
import com.example.fasthub.ui.mainActivity.bottomNav.IssuesFragment
import com.example.fasthub.ui.mainActivity.bottomNav.PullRequestFragment
import com.example.fasthub.R
import com.example.fasthub.databinding.HomeFragmentBinding
import com.example.fasthub.ui.mainActivity.MainActivity

class HomeFragment : Fragment() {
    private var _vb: HomeFragmentBinding? = null
    private val vb get() = _vb!!

    private val feedsFr = FeedsFragment()
    private val issuesFr = IssuesFragment()
    private val requestFr = PullRequestFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = HomeFragmentBinding.inflate(layoutInflater)
        setCurrentFragment(feedsFr)
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vb.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.feeds -> setCurrentFragment(feedsFr)
                R.id.issues -> setCurrentFragment(issuesFr)
                R.id.pull_requests -> setCurrentFragment(requestFr)
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment, fragment)
            .commit()
    }
}
