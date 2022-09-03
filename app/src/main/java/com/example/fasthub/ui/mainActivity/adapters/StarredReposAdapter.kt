package com.example.fasthub.ui.mainActivity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fasthub.data.model.GithubRepo
import com.example.fasthub.databinding.StarredRepoItemBinding
import com.example.fasthub.utils.formatter
import java.text.SimpleDateFormat
import java.util.*

class StarredReposAdapter(private val onClick: (GithubRepo) -> Unit) :
    RecyclerView.Adapter<StarredReposAdapter.StarredRepoViewHolder>() {

    private val starredRepos= mutableListOf<GithubRepo>()
    fun setItems(repos: List<GithubRepo>) {
        this.starredRepos.clear()
        this.starredRepos.addAll(repos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarredRepoViewHolder {
        return StarredRepoViewHolder(
            StarredRepoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StarredRepoViewHolder, position: Int) {
        val starredRepo = starredRepos[position]
        holder.apply {
            setStarredRepo(starredRepo)
            itemView.setOnClickListener { onClick(starredRepo) }
        }
    }

    override fun getItemCount(): Int = starredRepos.size

    inner class StarredRepoViewHolder(private val vb: StarredRepoItemBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun setStarredRepo(repo: GithubRepo) {
            val date = repo.updateDate
            vb.starredRepoUpdateDate.text = formatter.format(date)
            vb.repoName.text = repo.repoFullName
            vb.starCount.text = repo.stargazersCount.toString()
            vb.branchCount.text = repo.forksCount.toString()
            vb.starredRepoStorage.text = repo.size.toString()
        }
    }
}