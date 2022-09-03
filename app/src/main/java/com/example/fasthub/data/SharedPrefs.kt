package com.example.fasthub.data

import android.content.Context
import com.example.fasthub.utils.PREF_ACCESS_TOKEN
import com.example.fasthub.utils.PREF_MODE
import com.example.fasthub.utils.PREF_NAME

//class SharedPrefs @Inject constructor(private val preferences: SharedPreferences) {
class SharedPrefs (context: Context) {
    private val preferences = context.getSharedPreferences(PREF_NAME, PREF_MODE)

    val token
        get() = preferences.getString(PREF_ACCESS_TOKEN, null) ?: "null"

    val isLoggedIn: Boolean
        get() = preferences.getString(PREF_ACCESS_TOKEN, null) != null

    fun saveToken(accessToken: String) {
        val editor = preferences.edit()
        editor.putString(PREF_ACCESS_TOKEN, accessToken).apply()
    }
    fun deleteToken() {
        val editor = preferences.edit()
        editor.remove(PREF_ACCESS_TOKEN).apply()
    }
}

