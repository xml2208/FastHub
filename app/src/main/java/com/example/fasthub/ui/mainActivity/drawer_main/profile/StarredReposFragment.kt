package com.example.fasthub.ui.mainActivity.drawer_main.profile

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
import com.example.fasthub.databinding.FragmentStarredReposBinding
import com.example.fasthub.ui.mainActivity.adapters.StarredReposAdapter


class StarredReposFragment : Fragment() {
    private var _vb: FragmentStarredReposBinding? = null
    private val vb get() = _vb!!
    private val vm: ProfileViewModel by activityViewModels()
    private val starredRepoAdapter by lazy { StarredReposAdapter {} }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentStarredReposBinding.inflate(layoutInflater)
        vb.starredRepoList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = starredRepoAdapter
            return vb.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            vm.starredRepos.collect { repos ->
                starredRepoAdapter.setItems(repos)
            }
        }
    }

    private fun openStarredRepo(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}