package com.example.fasthub.ui.mainActivity.drawer_main.profile.repo_tree

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fasthub.data.model.GitRepoContentResponse
import com.example.fasthub.network.GithubApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoTreeViewModel @Inject constructor(val api: GithubApi) : ViewModel() {

    private val _fileList = MutableStateFlow<List<GitRepoContentResponse>>(emptyList())
    val fileList = _fileList.asStateFlow()

    private var _fileContent = MutableStateFlow("")
    val fileContent = _fileContent.asStateFlow()

    fun getFileContent(owner: String, repo: String, sha: String) {
        viewModelScope.launch {
            try {
                _fileContent.value = api.getRepoBlob(owner, repo, sha).content
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getListFiles(owner: String, repo: String, path: String) {
        viewModelScope.launch {
            try {
                _fileList.value = api.getRepoContents(owner, repo, path)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}