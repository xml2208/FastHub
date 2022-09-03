package com.example.fasthub.ui.mainActivity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.fasthub.data.model.Gists
import com.example.fasthub.databinding.PublicGistsItemBinding
import com.example.fasthub.utils.formatter

class PublicGistsAdapter(
    private val onCLickGist: (Gists) -> Unit
) : RecyclerView.Adapter<PublicGistsAdapter.GistsViewHolder>() {

    private val publicGists = mutableListOf<Gists>()
    fun setItems(items: List<Gists>) {
        this.publicGists.clear()
        this.publicGists.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistsViewHolder {
        return GistsViewHolder(
            PublicGistsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GistsViewHolder, position: Int) {
        val publicGist = publicGists[position]
        holder.apply {
            setPublicGists(publicGist)
            itemView.setOnClickListener { onCLickGist(publicGist) }
        }
    }

    override fun getItemCount(): Int = publicGists.size

    inner class GistsViewHolder(private val vb: PublicGistsItemBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun setPublicGists(gist: Gists) {
            val userName = gist.owner["login"].toString()
            val img = gist.owner["avatar_url"].toString()
            val time = gist.created_at
            val fileName = gist.files.keys.first().toString()

            vb.username.text = userName
            vb.gistOwnerImg.load(img)
            vb.createdAt.text = formatter.format(time)
            vb.fileName.text = fileName
        }
    }
}