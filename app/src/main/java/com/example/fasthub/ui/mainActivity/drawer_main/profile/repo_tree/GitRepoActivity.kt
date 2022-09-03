package com.example.fasthub.ui.mainActivity.drawer_main.profile.repo_tree

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fasthub.R
import com.example.fasthub.databinding.ActivityGitRepoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitRepoActivity : AppCompatActivity() {
    private lateinit var vb: ActivityGitRepoBinding
    private val vm: RepoTreeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityGitRepoBinding.inflate(layoutInflater)
        setContentView(vb.root)

        val repo = intent.extras?.get("repo").toString()
        val owner = intent.extras?.get("owner").toString()

        supportFragmentManager.beginTransaction()
            .replace(R.id.repo_tree_container, RepoFileListFragment.newInstance(owner, repo))
            .commit()
    }
}