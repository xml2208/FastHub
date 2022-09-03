package com.example.fasthub.ui.mainActivity.drawer_main.profile.repo_tree

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fasthub.R
import com.example.fasthub.databinding.FragmentRepoFileListBinding
import com.example.fasthub.ui.mainActivity.adapters.RepoFilesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoFileListFragment : Fragment() {
    private lateinit var _vb: FragmentRepoFileListBinding

    private val vm: RepoTreeViewModel by activityViewModels()
    private val repo: String by lazy { requireArguments().getString("repo", "") }
    private val owner: String by lazy { requireArguments().getString("owner", "") }
    private val path: String by lazy { requireArguments().getString("path", "") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _vb = FragmentRepoFileListBinding.inflate(layoutInflater)

        Log.d("ddk9499", "$owner/$repo/$path")

        val filesAdapter = RepoFilesAdapter(
            onFileClick = { openFileContent(it) },
            onFolderClick = { openFolderContent(it) }
        )

        _vb.fileListRView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = filesAdapter
        }

        lifecycleScope.launchWhenResumed {
            vm.fileList.collect { items ->
                filesAdapter.setItems(items)
            }
        }
        lifecycleScope.launchWhenResumed {
            vm.getListFiles(owner, repo, path)
        }
        return _vb.root
    }

    private fun openFolderContent(path: String) {
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.repo_tree_container, newInstanceWithPath(owner, repo, path))
            .commit()
    }

    private fun openFileContent(sha: String) {
        val fileContentFr = FileContentFragment.newInstance(owner, repo, sha)
        (activity as GitRepoActivity)
            .supportFragmentManager.beginTransaction()
            .replace(R.id.repo_tree_container, fileContentFr)
            .commit()
    }

    companion object {
        fun newInstance(owner: String, repo: String): RepoFileListFragment =
            RepoFileListFragment().also { fragment ->
                fragment.arguments = Bundle().also { bundle ->
                    bundle.putString("repo", repo)
                    bundle.putString("owner", owner)
                }
            }

        private fun newInstanceWithPath(
            owner: String,
            repo: String,
            path: String
        ): RepoFileListFragment = RepoFileListFragment().also { fragment ->
            fragment.arguments = Bundle().also { bundle ->
                bundle.putString("owner", owner)
                bundle.putString("repo", repo)
                bundle.putString("path", path)
            }
        }
    }
}
