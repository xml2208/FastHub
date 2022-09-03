package com.example.fasthub.ui.oauth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fasthub.utils.clientId
import com.example.fasthub.utils.clientSecret
import com.example.fasthub.data.model.AccessToken
import com.example.fasthub.network.GithubApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val githubApi: GithubApi) : ViewModel() {
    private var _accessToken = MutableLiveData<AccessToken>()
    val accessToken: LiveData<AccessToken> get() = _accessToken

    fun getAccessToken(code: String) {
        viewModelScope.launch {
                _accessToken.value =
                        githubApi.getAccessToken(clientId, clientSecret, code)
                Log.d("ViewModel", "AccessToken: ${accessToken.value?.accessToken}")
            }
        }
    }
