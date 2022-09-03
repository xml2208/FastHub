package com.example.fasthub.ui.mainActivity.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fasthub.data.model.GithubIssue
import com.example.fasthub.databinding.IssueItemBinding
import com.example.fasthub.utils.formatter
import java.text.SimpleDateFormat
import java.util.*

class GitIssueAdapter() :
    RecyclerView.Adapter<GitIssueAdapter.IssueVH>() {

    private val issues = mutableListOf<GithubIssue>()

    fun setItems(issues: List<GithubIssue>) {
        this.issues.clear()
        this.issues.addAll(issues)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueVH {
        return IssueVH(IssueItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: IssueVH, position: Int) {
        val issue = issues[position]
        holder.setIssue(issue)
    }

    override fun getItemCount(): Int = issues.size


    inner class IssueVH(private val vb: IssueItemBinding) : RecyclerView.ViewHolder(vb.root) {
        fun setIssue(issue: GithubIssue) {
            val login = issue.user["login"]
            val repoUrl = issue.repository_url
            val date = issue.created
            vb.searchIssueNum.text = issue.issueNum.toString()
            vb.searchIssueLogin.text = login.toString()
            vb.searchIssueRepo.text = repoUrl.substringAfterLast("/")
            vb.searchIssueTitle.text = issue.title
            vb.searchIssueTime.text = formatter.format(date)
        }
    }
}