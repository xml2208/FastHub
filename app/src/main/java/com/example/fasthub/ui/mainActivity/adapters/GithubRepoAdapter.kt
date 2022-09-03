package com.example.fasthub.ui.mainActivity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fasthub.data.model.GithubRepo
import com.example.fasthub.databinding.ItemGitRepoBinding
import com.example.fasthub.utils.formatter

class GithubRepoAdapter(
    private val onItemClicked: (String) -> Unit
) : RecyclerView.Adapter<GithubRepoAdapter.GitHubRepoViewHolder>() {

    private val githubRepos = mutableListOf<GithubRepo>()

    fun setItems(repos: List<GithubRepo>) {
        this.githubRepos.clear()
        this.githubRepos.addAll(repos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubRepoViewHolder {
        return GitHubRepoViewHolder(ItemGitRepoBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: GitHubRepoViewHolder, position: Int) {
        val repo = githubRepos[position]
        holder.setGitHubRepo(repo)
        holder.itemView.setOnClickListener { onItemClicked(repo.name) }
    }

    override fun getItemCount(): Int = githubRepos.size

    inner class GitHubRepoViewHolder(private var binding: ItemGitRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setGitHubRepo(gitHubRepo: GithubRepo) {
            try {
                val receivedDate = gitHubRepo.createdAt
                binding.repoUpdateDate.text = formatter.format(receivedDate)
                binding.textRepoName.text = gitHubRepo.name
                binding.textLanguage.text = gitHubRepo.language
                binding.repoStorage.text = gitHubRepo.size.toString()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}