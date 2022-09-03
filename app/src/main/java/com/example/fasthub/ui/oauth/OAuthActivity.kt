package com.example.fasthub.ui.oauth

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.fasthub.*
import com.example.fasthub.GithubApp
import com.example.fasthub.databinding.ActivityAuthBinding
import com.example.fasthub.ui.mainActivity.MainActivity
import com.example.fasthub.utils.authUrl
import com.example.fasthub.utils.clientId
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OAuthActivity : AppCompatActivity() {
    private lateinit var vb: ActivityAuthBinding
    private val vm: LoginViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        onNewIntent(intent)
        if (GithubApp.INSTANCE.prefs.isLoggedIn) {
            openMainActivity()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(vb.root)

        vb.tv.setOnClickListener {
            login()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val code = intent?.data?.getQueryParameter("code") ?: return
        vm.getAccessToken(code)
        vm.accessToken.observe(this@OAuthActivity) { token ->
            GithubApp.INSTANCE.prefs.saveToken(token.accessToken)
        }
        Log.d("Main Activity", "AccessToken: ${vm.accessToken.value?.accessToken}")
        if (GithubApp.INSTANCE.prefs.isLoggedIn) {
            openMainActivity()
        }
    }

    private fun openMainActivity() {
        Intent(this, MainActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(it)
        }
        finish()
    }

    private fun login() {
        val intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("$authUrl?client_id=$clientId&scope=user"))
        startActivity(intent)
    }
}

