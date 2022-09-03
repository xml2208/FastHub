package com.example.fasthub.network

import com.example.fasthub.data.model.*
import  com.example.fasthub.utils.DOMAIN_URL
import retrofit2.http.*

interface GithubApi {

    @POST("$DOMAIN_URL/login/oauth/access_token")
    @FormUrlEncoded
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): AccessToken

    @GET("user")
    suspend fun getUserData(@Header("Authorization") token: String): GithubUser

    @GET("users/{userName}")
    suspend fun getUser(@Path("userName") userName: String): GithubUser

    @GET("users/{owner}/followers")
    suspend fun getFollowersList(@Path("owner") owner: String): List<Follower>

    @GET("users/{owner}/following")
    suspend fun getFollowingList(@Path("owner") owner: String): List<Follower>

    @GET("user/repos")
    suspend fun getRepos(@Header("Authorization") token: String): List<GithubRepo>

    @GET("users/{owner}/starred")
    suspend fun getStarredRepos(@Path("owner") owner: String): List<GithubRepo>

    @GET("users/{owner}/received_events")
    suspend fun getFeeds(@Path("owner") owner: String): List<GithubFeed>

    @GET("repositories?q=android")
    suspend fun getTrendingRepos(
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("per_page") itemsPerPage: Int
    ): List<GithubRepo>

    @GET("gists/public")
    suspend fun getPublicGists(@Header("Authorization") token: String): List<Gists>

    @GET("gists/starred")
    suspend fun getStarredGists(@Header("Authorization") token: String): List<Gists>

    @GET("users/{owner}/gists")
    suspend fun getUserGists(@Path("owner") owner: String): List<Gists>

    @GET("search/repositories")
    suspend fun searchGitRepository(@Query("q") q: String): RepoSearchModel

    @GET("search/users")
    suspend fun searchGitUsers(@Query("q") q: String): UserSearchModel

    @GET("search/issues")
    suspend fun searchGitIssues(@Query("q") q: String): IssueSearchModel

    @GET("repos/{owner}/{repo}/contents/{path}")
    suspend fun getRepoContents(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("path") path: String,
    ): List<GitRepoContentResponse>

    @GET("repos/{owner}/{repo}/git/blobs/{sha}")
    suspend fun getRepoBlob(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("sha") sha: String
    ): GitRepoBlob

//    @DELETE("applications/{clientId}/token")
//    @FormUrlEncoded
    @HTTP(method = "DELETE", path ="applications/{clientId}/token", hasBody = true)
    suspend fun logout(
        @Header("Authorization") token: String,
        @Path("clientId") clientId: String,
        @Body accessToken: String
    ): Map<*, *>


//https://api.github.com/repos/xml2208/Forage/git/blobs/c6b62868a73f9dc6620b6ff962aca34c806d7bfe
//https://api.github.com/users/xml2208/starred
}