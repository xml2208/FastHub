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
import com.example.fasthub.databinding.FragmentPublicGistsBinding
import com.example.fasthub.ui.mainActivity.adapters.PublicGistsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PublicGistsFragment : Fragment() {
    private var _vb: FragmentPublicGistsBinding? = null
    private val vb get() = _vb!!
    private val vm: GistsViewModel by viewModels()
    private val publicGistsAdapter by lazy { PublicGistsAdapter {} }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _vb = FragmentPublicGistsBinding.inflate(layoutInflater)

        vb.rView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = publicGistsAdapter
        }
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            vm.publicGists.collect { gists ->
                publicGistsAdapter.setItems(gists)
            }
        }
    }

    private fun openGist(gistUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(gistUrl))
        startActivity(intent)
    }

    companion object {
        fun newInstance(): PublicGistsFragment {
            return PublicGistsFragment()
        }
    }
}