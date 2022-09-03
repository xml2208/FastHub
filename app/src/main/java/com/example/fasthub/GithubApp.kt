package com.example.fasthub

import android.app.Application
import com.example.fasthub.data.SharedPrefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GithubApp : Application() {

    val prefs by lazy { SharedPrefs(applicationContext) }

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

    }

    companion object {
        lateinit var INSTANCE: GithubApp
            private set
    }
}