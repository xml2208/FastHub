package com.example.fasthub.ui.mainActivity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fasthub.data.model.Gists
import com.example.fasthub.databinding.UserGistsItemBinding
import com.example.fasthub.utils.formatter

class UserGistsAdapter(private val onClicked: (Gists) -> Unit) :
    RecyclerView.Adapter<UserGistsAdapter.UserGistHolder>() {

    private val userGists = mutableListOf<Gists>()
    fun setItems(gists: List<Gists>) {
        this.userGists.clear()
        this.userGists.addAll(gists)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserGistHolder {
        return UserGistHolder(
            UserGistsItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserGistHolder, position: Int) {
        val gist = userGists[position]
        holder.apply {
            setUserGist(gist)
            itemView.setOnClickListener { onClicked(gist) }
        }
    }

    override fun getItemCount(): Int = userGists.size

    inner class UserGistHolder(private val vb: UserGistsItemBinding) :
        RecyclerView.ViewHolder(vb.root) {
        fun setUserGist(gists: Gists) {
            vb.gistCreationTime.text = formatter.format(gists.created_at)
            vb.gistFileName.text = gists.files.keys.first().toString()
        }
    }
}