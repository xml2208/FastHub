package com.example.fasthub.ui.mainActivity.bottomNav

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.fasthub.data.model.GithubFeed
import com.example.fasthub.databinding.ItemGitFeedsBinding
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class FeedsAdapter(private val feeds: List<GithubFeed>, val onItemClicked: (GithubFeed) -> Unit) :
    RecyclerView.Adapter<FeedsAdapter.FeedsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsHolder {
        return FeedsHolder(ItemGitFeedsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FeedsHolder, position: Int) {
        val feed = feeds[position]
        holder.bind(feed)
        holder.itemView.setOnClickListener { onItemClicked(feed) }
    }

    override fun getItemCount(): Int =
        feeds.size

    inner class FeedsHolder(private val vb: ItemGitFeedsBinding) :
        RecyclerView.ViewHolder(vb.root) {

        private val formatter: SimpleDateFormat by lazy { SimpleDateFormat("MMM dd,yyyy", Locale.US) }

        fun bind(feed: GithubFeed) {
            try {
                val login = feed.actor["login"]
                val action = feed.type.lowercase()
                val repo = feed.repo["name"].toString()
                val img = feed.actor["avatar_url"]

                vb.img.load(img)
                vb.login.text = login.toString()
                vb.action.text = action
                vb.repoName.text = repo
                vb.timelineUrl.text = formatter.format(feed.createdAt)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}