package com.example.fasthub.data.model

import com.google.gson.annotations.SerializedName

class TrendingRepoResponse(
    @SerializedName("total_count")
    val total: Int,

    @SerializedName("items")
    val items: List<GithubRepo>
)