package com.example.fasthub.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

//@Parcelize
data class GithubUser(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("gists_url") var gistsUrl: String? = "",
    @SerializedName("login") var login: String? = "",
    @SerializedName("email") var email: String? = "",
    @SerializedName("avatar_url") var avatarUrl: String? = "",
    @SerializedName("bio") val bio: String? = "",
    @SerializedName("followers") val followers: Int,
    @SerializedName("following") val following: Int,
    @SerializedName("location") val location: String,
    @SerializedName("created_at") val createdAt: Date
)