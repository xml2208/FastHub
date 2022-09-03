package com.example.fasthub.ui.mainActivity.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fasthub.databinding.RepoSearchFrBinding
import com.example.fasthub.ui.mainActivity.adapters.GithubRepoAdapter

class RepoSearchFragment : Fragment() {
    private var _vb: RepoSearchFrBinding? = null
    private val vb get() = _vb!!
    private val vm: SearchViewModel by activityViewModels()
    private val repoAdapter by lazy { GithubRepoAdapter {} }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = RepoSearchFrBinding.inflate(layoutInflater)

        vb.repoSearchRView.layoutManager = LinearLayoutManager(requireContext())
        vb.repoSearchRView.adapter = repoAdapter

        lifecycleScope.launchWhenResumed {
            vm.searchRepoResponse.collect { searchModel ->
                repoAdapter.setItems(searchModel!!.items)
            }
        }
        return vb.root
    }

    companion object {
        fun newInstance(): RepoSearchFragment {
            return RepoSearchFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        arguments?.clear()
        _vb = null
    }
}