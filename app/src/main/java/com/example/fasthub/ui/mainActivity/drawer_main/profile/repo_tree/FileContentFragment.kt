package com.example.fasthub.ui.mainActivity.drawer_main.profile.repo_tree

import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.fasthub.databinding.FragmentFileContentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FileContentFragment : Fragment() {
    private lateinit var _vb: FragmentFileContentBinding
    private val vm: RepoTreeViewModel by activityViewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentFileContentBinding.inflate(layoutInflater)
        val sha = arguments?.get("sha").toString()
        val repo = arguments?.get("repo").toString()
        val owner = arguments?.get("owner").toString()

        lifecycleScope.launchWhenResumed {
            vm.fileContent.collect {
                val decodedBytes = Base64.decode(it, 0)
                val decodedString = String(decodedBytes)
                _vb.contentTv.text = decodedString
            }
        }

        lifecycleScope.launchWhenResumed {
            vm.getFileContent(owner, repo, sha)
        }
        return _vb.root
    }

    companion object {
        fun newInstance(owner: String, repo: String, sha: String): FileContentFragment {
            return FileContentFragment().also { fr ->
                fr.arguments = Bundle().also { bundle ->
                    bundle.putString("owner", owner)
                    bundle.putString("repo", repo)
                    bundle.putString("sha", sha)
                }
            }
        }
    }
}