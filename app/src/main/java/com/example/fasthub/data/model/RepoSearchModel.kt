package com.example.fasthub.data.model

import com.google.gson.annotations.SerializedName

data class RepoSearchModel(
    @SerializedName("total_count") val totalCount: Int = 0,
    @SerializedName("items") val items: List<GithubRepo>
)

data class UserSearchModel(
    @SerializedName("total_count") val totalCount: Int = 0,
    @SerializedName("items") val items: List<Follower>
)

data class GistSearchModel(
    @SerializedName("total_count") val totalCount: Int = 0,
    @SerializedName("items") val items: List<Gists>
)

data class IssueSearchModel(
    @SerializedName("total_count") val totalCount: Int = 0,
    @SerializedName("items") val items: List<GithubIssue>
)

data class CodeSearchModel(
    @SerializedName("total_count") val totalCount: Int = 0,
    @SerializedName("items") val items: List<GithubUser>
)
