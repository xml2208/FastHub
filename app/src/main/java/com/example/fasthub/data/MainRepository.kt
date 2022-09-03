package com.example.fasthub.data

import android.content.Context
import java.lang.IllegalStateException

class MainRepository(context: Context) {
//    private val prefManager = SharedPrefs(context)


    companion object {
        private var INSTANCE: MainRepository? = null
        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = MainRepository(context)

            }
        }
        fun get(): MainRepository {
            return INSTANCE
                ?: throw IllegalStateException("MainRepository must be initialized!!")
        }
    }
}