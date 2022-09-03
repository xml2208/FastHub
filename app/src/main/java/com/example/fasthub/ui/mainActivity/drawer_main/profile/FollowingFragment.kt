package com.example.fasthub.ui.mainActivity.drawer_main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fasthub.databinding.FragmentFollowingBinding
import com.example.fasthub.ui.mainActivity.adapters.UserAdapter

class FollowingFragment : Fragment() {
    private var _vb: FragmentFollowingBinding? = null
    private val vb get() = _vb!!
    private val vm: ProfileViewModel by activityViewModels()
    private val adapter by lazy { UserAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentFollowingBinding.inflate(layoutInflater)

        vb.followingsList.layoutManager = LinearLayoutManager(requireContext())
        vb.followingsList.adapter = adapter

        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            vm.followings.collect { list ->
                adapter.setItems(list)
            }
        }
    }
}