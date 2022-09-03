package com.example.fasthub.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Gists(
    @SerializedName("owner") val owner: Map<String, Any>,
    @SerializedName("files") val files: Map<String, Any>,
@SerializedName("created_at") val created_at: Date,
    @SerializedName("id") val id: String,
)