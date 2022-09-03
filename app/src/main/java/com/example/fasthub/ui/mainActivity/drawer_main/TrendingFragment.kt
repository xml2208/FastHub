package com.example.fasthub.ui.mainActivity.drawer_main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fasthub.databinding.FragmentTrendingBinding
import com.example.fasthub.ui.mainActivity.adapters.TrendingReposAdapter
import com.example.fasthub.ui.mainActivity.drawer_main.profile.ProfileViewModel
import com.example.fasthub.utils.DOMAIN_URL

class TrendingFragment : Fragment() {
    private lateinit var _vb: FragmentTrendingBinding
    private val vm: ProfileViewModel by activityViewModels()
    private val trendingRepoAdapter by lazy { TrendingReposAdapter {
        openGitRepo(it.repoFullName)
    } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentTrendingBinding.inflate(layoutInflater)

        _vb.rView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = trendingRepoAdapter
        }

        return _vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            vm.trendingRepos.collect { repoList ->
                trendingRepoAdapter.setItems(repoList)
            }
        }
    }

    private fun openGitRepo(repoName: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("$DOMAIN_URL/${repoName}"))
        startActivity(intent)
    }
}