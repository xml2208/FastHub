package com.example.fasthub.ui.mainActivity.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fasthub.databinding.UserSearchFrBinding
import com.example.fasthub.ui.mainActivity.adapters.UserAdapter

class UserSearchFragment : Fragment() {
    private var _vb: UserSearchFrBinding? = null
    private val vb get() = _vb!!
    private val vm: SearchViewModel by activityViewModels()
    private val adapter by lazy { UserAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = UserSearchFrBinding.inflate(layoutInflater)

        vb.userSearchList.layoutManager = LinearLayoutManager(requireContext())
        vb.userSearchList.adapter = adapter

        lifecycleScope.launchWhenResumed {
            vm.searchUserResponse.collect { userModel ->
                adapter.setItems(userModel!!.items)
            }
        }
        return vb.root
    }

    companion object {
        fun newInstance(): UserSearchFragment {
            return UserSearchFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }
}