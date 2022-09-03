package com.example.fasthub.data.model

import com.google.gson.annotations.SerializedName

data class GistCreation(
    @SerializedName("description") val description: String,
    @SerializedName("files") val files: Map<String, Any>
)