package com.example.fasthub.ui.mainActivity.drawer_main.gists

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
import com.example.fasthub.databinding.FragmentUserGistsBinding
import com.example.fasthub.ui.mainActivity.adapters.UserGistsAdapter
import com.example.fasthub.ui.mainActivity.drawer_main.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserGistsFragment : Fragment() {
    private var _vb: FragmentUserGistsBinding? = null
    private val vb get() = _vb!!
    private val vm: ProfileViewModel by activityViewModels()
    private val userGistsAdapter by lazy { UserGistsAdapter {} }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentUserGistsBinding.inflate(layoutInflater)
        vb.userGistsRView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = userGistsAdapter
            return vb.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            vm.userGists.collect {
                userGistsAdapter.setItems(it)
            }
        }
    }


    private fun openGist(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    companion object {
        fun newInstance(): UserGistsFragment {
            return UserGistsFragment()
        }
    }
}