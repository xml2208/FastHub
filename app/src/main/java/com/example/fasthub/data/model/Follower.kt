package com.example.fasthub.data.model

import com.google.gson.annotations.SerializedName

class Follower(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val img_url: String
)