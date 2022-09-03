package com.example.fasthub.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class GithubRepo(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val repoFullName: String,
    @SerializedName("owner") val owner: Map<Any, String>,
    @SerializedName("private") val isPrivate: Boolean,
    @SerializedName("language") val language: String,
    @SerializedName("updated_at") val updateDate: Date,
    @SerializedName("size") val size: Int,
    @SerializedName("created_at") val createdAt: Date,
    @SerializedName("blobs_url") val blobsUrl: String,  //"https://api.github.com/repos/xml2208/Amphibians/git/blobs{/sha}"
    @SerializedName("watchers_count") val watchersCount: Int,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("description") val description: String,
)