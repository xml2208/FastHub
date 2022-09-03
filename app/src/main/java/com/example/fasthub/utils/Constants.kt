package com.example.fasthub.utils

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

const val clientId = "173910ff11a299008e6d"
const val clientSecret = "b3e3544f392fcadd1f9be6a7cdf446d34900511f"
const val authUrl = "https://github.com/login/oauth/authorize"
const val gistUrl = "https://gist.github.com/"
const val API_URL =  "https://api.github.com/"
const val DOMAIN_URL = "https://github.com"
const val redirectUri = "demo://com.example.fasthub"
const val EXTRA_ACCESS_TOKEN = "AccessToken"
const val EXTRA_USER_DATA = "AccessToken"
const val PREF_NAME = "GithubApp"
const val PREF_MODE = Context.MODE_PRIVATE
const val PREF_ACCESS_TOKEN = "ACCESS_TOKEN"
const val CACHE_SIZE = (10 * 1024 * 1024).toLong() // 10 MB
val formatter: SimpleDateFormat by lazy { SimpleDateFormat("MMM dd,yyyy", Locale.US) }