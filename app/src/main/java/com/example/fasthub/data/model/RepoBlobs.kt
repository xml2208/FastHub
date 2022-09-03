package com.example.fasthub.data.model

import com.google.gson.annotations.SerializedName

data class RepoBlobs(
    @SerializedName("path") val fileName: String,
    @SerializedName("url") val url: String,

)