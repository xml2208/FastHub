package com.example.fasthub.ui.mainActivity.drawer_main.gists

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fasthub.databinding.FragmentStarredGistsBinding
import com.example.fasthub.ui.mainActivity.adapters.StarredGistsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarredGistsFragment : Fragment() {
    private var _vb: FragmentStarredGistsBinding? = null
    private val vb get() = _vb!!
    private val vm: GistsViewModel by viewModels()
    private val starredGistsAdapter by lazy { StarredGistsAdapter {} }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _vb = FragmentStarredGistsBinding.inflate(layoutInflater)

        vb.rView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = starredGistsAdapter
        }
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            vm.starredGists.collect { gists ->
                starredGistsAdapter.setItems(gists)
            }
        }
    }

    private fun openGist(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    companion object {
        fun newInstance(): StarredGistsFragment {
            return StarredGistsFragment()
        }
    }
}