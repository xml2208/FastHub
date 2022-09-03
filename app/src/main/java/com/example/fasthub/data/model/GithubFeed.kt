package com.example.fasthub.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class GithubFeed(
    @SerializedName("actor") val actor: Map<String, Any>,
    @SerializedName("payload") val payload: Map<String, Any>,
    @SerializedName("repo") val repo: Map<String, Any>,
    @SerializedName("type") val type: String,
    @SerializedName("created_at") val createdAt: Date
)