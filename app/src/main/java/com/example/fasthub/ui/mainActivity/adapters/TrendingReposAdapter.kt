package com.example.fasthub.ui.mainActivity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fasthub.data.model.GithubRepo
import com.example.fasthub.databinding.TrendingRepoItemBinding

class TrendingReposAdapter(val onClick: (GithubRepo) -> Unit) :
    RecyclerView.Adapter<TrendingReposAdapter.TrendingRepoHolder>() {

    private val repos = mutableListOf<GithubRepo>()
    fun setItems(repos: List<GithubRepo>) {
        this.repos.clear()
        this.repos.addAll(repos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingRepoHolder {
        return TrendingRepoHolder(
            TrendingRepoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TrendingRepoHolder, position: Int) {
        val trendingRepo = repos[position]
        holder.setTrendingRepo(trendingRepo)
        holder.itemView.setOnClickListener { onClick(trendingRepo) }
    }

    override fun getItemCount(): Int = repos.size

    inner class TrendingRepoHolder(private val vb: TrendingRepoItemBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun setTrendingRepo(trendingRepo: GithubRepo) {
//            vb.language.text = trendingRepo.language
            vb.repoFullName1.text = trendingRepo.repoFullName
            vb.descriptionRepo.text = trendingRepo.description
            vb.repoFullName.text = trendingRepo.repoFullName
            vb.starCount.text = trendingRepo.stargazersCount.toString()
            vb.branchCount.text = trendingRepo.forksCount.toString()
        }
    }
}
