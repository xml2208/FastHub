package com.example.fasthub.ui.mainActivity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.fasthub.data.model.Follower
import com.example.fasthub.databinding.FollowingItemBinding

class UserAdapter() :
    RecyclerView.Adapter<UserAdapter.FollowingViewHolder>() {

    private val items = mutableListOf<Follower>()

    fun setItems(followings: List<Follower>) {
        this.items.clear()
        this.items.addAll(followings)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        return FollowingViewHolder(
            FollowingItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        val item = items[position]
        holder.apply {
            setFollowingUser(item)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class FollowingViewHolder(private val vb: FollowingItemBinding) :
        RecyclerView.ViewHolder(vb.root) {
        fun setFollowingUser(user: Follower) {
            vb.login.text = user.login
            vb.imgLogin.load(user.img_url)
        }
    }
}