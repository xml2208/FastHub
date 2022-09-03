package com.example.fasthub.di

import android.content.Context
import com.example.fasthub.network.*
import com.example.fasthub.utils.API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleApp {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient1(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(logInterceptor)
//            .addInterceptor(offlineInterceptor)
//            .addNetworkInterceptor(onlineInterceptor)
//            .cache(Cache(Context.Cachzz))
            .authenticator(authenticator)
            .build()
    }

    @Provides
    @Singleton
    fun githubApiService(retrofit: Retrofit): GithubApi {
        return provideRetrofit(provideOkHttpClient1())
            .create(GithubApi::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideSharedPreferences (@ApplicationContext context: Context): SharedPreferences =
//        context.getSharedPreferences(PREF_NAME, PREF_MODE)
//
//    @Provides
//    @Singleton
//    fun provideSharedPrefs(preferences: SharedPreferences) =
//        SharedPrefs(preferences)
}
