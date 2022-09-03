package com.example.fasthub.ui.mainActivity.bottomNav

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fasthub.data.model.GithubFeed
import com.example.fasthub.databinding.FragmentFeedsBinding
import com.example.fasthub.ui.mainActivity.MainActivity
import com.example.fasthub.ui.mainActivity.drawer_main.profile.ProfileViewModel
import com.example.fasthub.utils.DOMAIN_URL

class FeedsFragment : Fragment() {
    private lateinit var _vb: FragmentFeedsBinding
    private val vm: ProfileViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentFeedsBinding.inflate(layoutInflater)

        _vb.rv.layoutManager = LinearLayoutManager(requireContext())


        lifecycleScope.launchWhenResumed {
            vm.feeds.collect { feeds ->
                _vb.rv.adapter = FeedsAdapter(feeds) {
                    openGitRepo(it.repo["name"].toString())
                }
            }
        }
        return _vb.root
    }

    private fun openGitRepo(repoName: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("$DOMAIN_URL/$repoName"))
        startActivity(intent)
    }
}

