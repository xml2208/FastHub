package com.example.fasthub.data.model

import com.google.gson.annotations.SerializedName

data class GitRepoTree(
    @SerializedName("sha") val sha: String,
    @SerializedName("tree") val tree: List<RepoBlobs>,

)