package com.example.fasthub.ui.mainActivity.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fasthub.data.model.IssueSearchModel
import com.example.fasthub.data.model.RepoSearchModel
import com.example.fasthub.data.model.UserSearchModel
import com.example.fasthub.network.GithubApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val api: GithubApi) : ViewModel() {
    private var _searchRepoResponse =
        MutableStateFlow<RepoSearchModel?>(RepoSearchModel(0, emptyList()))
    val searchRepoResponse = _searchRepoResponse.asStateFlow()

    private var _searchUserResponse =
        MutableStateFlow<UserSearchModel?>(UserSearchModel(0, emptyList()))
    val searchUserResponse = _searchUserResponse

    private var _searchIssueResponse =
        MutableStateFlow<IssueSearchModel?>(IssueSearchModel(0, emptyList()))
    val searchIssueResponse = _searchIssueResponse

    val query: MutableLiveData<String> = MutableLiveData("null")

    fun getArgument(str: String) {
        query.value = str
    }

    fun queryForSearchApi(q: String) {
        viewModelScope.launch {
            try {
                _searchRepoResponse.value = api.searchGitRepository(q)
                _searchUserResponse.value = api.searchGitUsers(q)
                _searchIssueResponse.value = api.searchGitIssues(q)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}