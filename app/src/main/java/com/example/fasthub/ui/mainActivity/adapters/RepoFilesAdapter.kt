package com.example.fasthub.ui.mainActivity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fasthub.R
import com.example.fasthub.data.model.GitRepoContentResponse
import com.example.fasthub.data.model.GitRepoFileType
import com.example.fasthub.databinding.RepoFilesItemBinding

class RepoFilesAdapter(
    private val onFileClick: (String) -> Unit,
    private val onFolderClick: (String) -> Unit,
) : RecyclerView.Adapter<RepoFilesAdapter.RepoFileVH>() {

    private val repoFiles = mutableListOf<GitRepoContentResponse>()

    fun setItems(items: List<GitRepoContentResponse>) {
        this.repoFiles.clear()
        this.repoFiles.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoFileVH {
        return RepoFileVH(
            RepoFilesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoFileVH, position: Int) {
        holder.setItem(repoFiles[position])
    }

    override fun getItemCount(): Int = repoFiles.size

    inner class RepoFileVH(
        private val vb: RepoFilesItemBinding
    ) : RecyclerView.ViewHolder(vb.root) {

        private lateinit var repoItem: GitRepoContentResponse

        init {
            vb.root.setOnClickListener {
                when (repoItem.type) {
                    GitRepoFileType.FILE -> onFileClick(repoItem.sha)
                    GitRepoFileType.DIRECTORY -> onFolderClick(repoItem.path)
                }
            }
        }

        fun setItem(repoFile: GitRepoContentResponse) {
            repoItem = repoFile

            vb.repoFileImg.setImageResource(
                when (repoItem.type) {
                    GitRepoFileType.FILE -> R.drawable.ic_file_document
                    GitRepoFileType.DIRECTORY -> R.drawable.ic_folder
                }
            )
            vb.repoFileName.text = repoItem.path
            vb.repoFileSize.text = repoItem.size.toString()
        }
    }
}