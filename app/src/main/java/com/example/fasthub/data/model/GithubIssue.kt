package com.example.fasthub.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class GithubIssue(
    @SerializedName("number") val issueNum: Int,
    @SerializedName("title") val title: String,
    @SerializedName("created_at") val created: Date,
    @SerializedName("repository_url") val repository_url: String,
    @SerializedName("user") val user: Map<String, *>,
)