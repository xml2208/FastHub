package com.example.fasthub.ui.mainActivity.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fasthub.databinding.IssueSearchFrBinding
import com.example.fasthub.ui.mainActivity.adapters.GitIssueAdapter

class IssueSearchFr: Fragment() {
    private var _vb: IssueSearchFrBinding? = null
    private val vb get() = _vb!!
    private val vm: SearchViewModel by activityViewModels()
    private val issueAdapter by lazy { GitIssueAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = IssueSearchFrBinding.inflate(layoutInflater)

        vb.issueSearchList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = issueAdapter
        }

        lifecycleScope.launchWhenResumed {
            vm.searchIssueResponse.collect { issueModel ->
                issueAdapter.setItems(issueModel!!.items)
            }
        }

        return vb.root
    }

    companion object {
        fun newInstance(): IssueSearchFr {
            return IssueSearchFr()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
        arguments?.clear()
    }
}