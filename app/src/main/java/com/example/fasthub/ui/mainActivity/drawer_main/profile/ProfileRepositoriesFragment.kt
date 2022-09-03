package com.example.fasthub.ui.mainActivity.drawer_main.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fasthub.databinding.ProfileRepositoriesBinding
import com.example.fasthub.ui.mainActivity.adapters.GithubRepoAdapter
import com.example.fasthub.ui.mainActivity.drawer_main.profile.repo_tree.GitRepoActivity

class ProfileRepositoriesFragment : Fragment() {
    private var _vb: ProfileRepositoriesBinding? = null
    private val vb get() = _vb!!
    private val vm: ProfileViewModel by activityViewModels()
    private val repoAdapter by lazy {
        GithubRepoAdapter {
            openGitRepo(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = ProfileRepositoriesBinding.inflate(layoutInflater)
        vb.rView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = repoAdapter
        }
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            vm.repos.collect { repos ->
                repoAdapter.setItems(repos)
            }
        }
    }

    private fun openGitRepo(repo: String) {
        val intent = Intent(requireActivity(), GitRepoActivity::class.java)
        lifecycleScope.launchWhenResumed {
            vm.userName.collect { owner ->
                intent.putExtra("owner", owner)
            }
        }
        intent.putExtra("repo", repo)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }
}