package com.example.fasthub.data.model

import com.google.gson.annotations.SerializedName

data class GitRepoBlob(
    @SerializedName("content") val content: String,
)
