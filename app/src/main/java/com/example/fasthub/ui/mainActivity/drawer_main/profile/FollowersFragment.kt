package com.example.fasthub.ui.mainActivity.drawer_main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fasthub.databinding.FragmentFollowersBinding
import com.example.fasthub.ui.mainActivity.adapters.UserAdapter

class FollowersFragment : Fragment() {
    private var _vb: FragmentFollowersBinding? = null
    private val vb get() = _vb!!
    private val vm: ProfileViewModel by activityViewModels()
    private val adapter by lazy { UserAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentFollowersBinding.inflate(layoutInflater)

        vb.followersList.layoutManager = LinearLayoutManager(requireContext())
        vb.followersList.adapter = adapter

        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vb.followersList.layoutManager = LinearLayoutManager(requireContext())
        vb.followersList.adapter = adapter

        lifecycleScope.launchWhenResumed {
            vm.follower.collect { list ->
                adapter.setItems(list)
            }
        }
    }
}