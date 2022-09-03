package com.example.fasthub.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.fasthub.GithubApp
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor

val headerInterceptor: Interceptor = Interceptor { chain ->
    var request = chain.request()
    request = request.newBuilder()
        .addHeader("Accept", "application/vnd.github+json")
        .build()
    chain.proceed(request)
}

val authenticator = Authenticator { _, response ->
    var requestAvailable: Request? = null
    val token = GithubApp.INSTANCE.prefs.token
    requestAvailable = response.request.newBuilder()
        .header("Authorization", "token $token")
        .build()
    requestAvailable
}

val onlineInterceptor = Interceptor { chain ->
    val response = chain.proceed(chain.request())
    val maxAge = 60
    response.newBuilder()
        .header("Cache-Control", "public, max-age=" + maxAge)
        .removeHeader("Pragma")
        .build()
}

val offlineInterceptor = Interceptor { chain ->
    val request = chain.request()
    if (!isInternetAvailable(GithubApp())) {
        val maxStale = 60 * 60 * 24 * 30
        request.newBuilder()
            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
            .build()
    }
    chain.proceed(chain.request())
}

val logInterceptor = HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY }

fun isInternetAvailable(context: Context): Boolean {
    var isConnected: Boolean = false // Initial Value
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}

//val cache = Cache(context.cacheDir, cacheSize)
