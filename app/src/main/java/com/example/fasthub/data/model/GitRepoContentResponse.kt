package com.example.fasthub.data.model

import com.google.gson.annotations.SerializedName

data class GitRepoContentResponse(
    @SerializedName("path") val path: String,
    @SerializedName("sha") val sha: String,
    @SerializedName("size") val size: Int,
    @SerializedName("type") val type: GitRepoFileType,
    @SerializedName("git_url") val gitUrl: String,
    ) //https://api.github.com/repos/xml2208/Forage/git/blobs/aa724b77071afcbd9bb398053e05adaf7ac9405a

enum class GitRepoFileType {
    @SerializedName("file")
    FILE,

    @SerializedName("dir")
    DIRECTORY,
}