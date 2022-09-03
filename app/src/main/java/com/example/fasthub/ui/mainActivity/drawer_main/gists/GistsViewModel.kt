package com.example.fasthub.ui.mainActivity.drawer_main.gists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fasthub.GithubApp
import com.example.fasthub.data.model.Gists
import com.example.fasthub.network.GithubApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GistsViewModel @Inject constructor(private val api: GithubApi) : ViewModel() {

    private val token = GithubApp.INSTANCE.prefs.token
    private var _publicGists = MutableStateFlow<List<Gists>>(emptyList())
    private var _starredGists = MutableStateFlow<List<Gists>>(emptyList())

    val publicGists = _publicGists.asStateFlow()
    val starredGists = _starredGists.asStateFlow()

    init {
        try {
            viewModelScope.launch {
                _publicGists.value = api.getPublicGists(token)
                _starredGists.value = api.getStarredGists(token)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun createGist(files: Map<String, Map<*, *>>, description: String, public: Boolean) {
        viewModelScope.launch {
//            api.createGist(files, description, public)
        }
    }
}