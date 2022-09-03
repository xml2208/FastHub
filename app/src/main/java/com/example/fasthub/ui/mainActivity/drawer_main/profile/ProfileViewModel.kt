package com.example.fasthub.ui.mainActivity.drawer_main.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fasthub.GithubApp
import com.example.fasthub.data.model.*
import com.example.fasthub.network.GithubApi
import com.example.fasthub.utils.clientId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
//class ProfileViewModel @Inject constructor(private val gitApi: GithubApi, private val sp: SharedPrefs) :

class ProfileViewModel @Inject constructor(private val gitApi: GithubApi) :
    ViewModel() {

    private val token = GithubApp.INSTANCE.prefs.token
    private var _img = MutableStateFlow("")
    private var _userName = MutableStateFlow("")
    private var _profileName = MutableStateFlow("")
    private var _bio = MutableStateFlow("")
    private var _location = MutableStateFlow("")
    private var _createdAt = MutableStateFlow(Date())
    private var _repos = MutableStateFlow<List<GithubRepo>>(emptyList())
    private var _starredRepos = MutableStateFlow<List<GithubRepo>>(emptyList())
    private var _feeds = MutableStateFlow<List<GithubFeed>>(emptyList())
    private var _trendingRepos = MutableStateFlow<List<GithubRepo>>(emptyList())
    private var _userGists = MutableStateFlow<List<Gists>>(emptyList())
    private var _followings = MutableStateFlow<List<Follower>>(emptyList())
    private var _followers = MutableStateFlow<List<Follower>>(emptyList())

    val img = _img.asStateFlow()
    val userName = _userName.asStateFlow()
    val profileName = _profileName.asStateFlow()
    val bio = _bio.asStateFlow()
    val location = _location.asStateFlow()
    val createdAt = _createdAt.asStateFlow()
    val repos = _repos.asStateFlow()
    val feeds = _feeds.asStateFlow()
    val trendingRepos = _trendingRepos.asStateFlow()
    val userGists = _userGists.asStateFlow()
    val starredRepos = _starredRepos.asStateFlow()
    val followings = _followings.asStateFlow()
    val follower = _followers.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val trendingRepoResponse = gitApi.getTrendingRepos("stars", "desc", 25)
                _trendingRepos.value = trendingRepoResponse
                _repos.value = gitApi.getRepos(token)
                _img.value = gitApi.getUserData(token).avatarUrl.toString()
                _userName.value = gitApi.getUserData(token).login.toString()
                _profileName.value = gitApi.getUserData(token).name
                _bio.value = gitApi.getUserData(token).bio.toString()
                _location.value = gitApi.getUserData(token).location
                _createdAt.value = gitApi.getUserData(token).createdAt
                _feeds.value = gitApi.getFeeds(_userName.value)
                _userGists.value = gitApi.getUserGists(_userName.value)
                _starredRepos.value = gitApi.getStarredRepos(_userName.value)
                _followings.value = gitApi.getFollowingList(_userName.value)
                _followers.value = gitApi.getFollowersList(_userName.value)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun revokeAccess() {
        viewModelScope.launch {
            gitApi.logout(token, clientId, token)
        }
    }
}
