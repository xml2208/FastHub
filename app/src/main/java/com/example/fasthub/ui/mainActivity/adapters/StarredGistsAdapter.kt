package com.example.fasthub.ui.mainActivity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.fasthub.data.model.Gists
import com.example.fasthub.databinding.StarredGistItemBinding
import com.example.fasthub.utils.formatter

class StarredGistsAdapter(private val onCLick: (Gists) -> Unit) :
    RecyclerView.Adapter<StarredGistsAdapter.StarredGistHolder>() {

    private val starredGists = mutableListOf<Gists>()
    fun setItems(gists: List<Gists>) {
        this.starredGists.clear()
        this.starredGists.addAll(gists)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarredGistHolder {
        return StarredGistHolder(
            StarredGistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StarredGistHolder, position: Int) {
        val gist = starredGists[position]
        holder.apply {
            setStarredGist(gist)
            itemView.setOnClickListener { onCLick(gist) }
        }
    }

    override fun getItemCount(): Int = starredGists.size

    inner class StarredGistHolder(private val vb: StarredGistItemBinding) :
        RecyclerView.ViewHolder(vb.root) {
        fun setStarredGist(gists: Gists) {
            val img = gists.owner["avatar_url"]
            val userName = gists.owner["login"].toString()
            val fileName = gists.files.keys.first().toString()
            val createdTime = gists.created_at
            vb.createdAt.text = formatter.format(createdTime)
            vb.gistOwnerImg.load(img.toString())
            vb.username.text = userName
            vb.fileName.text = fileName
        }
    }
}