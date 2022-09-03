package com.example.fasthub.ui.mainActivity.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.example.fasthub.R
import com.example.fasthub.ui.mainActivity.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    private val vm: SearchViewModel by viewModels()
    private lateinit var toolbar: Toolbar
    private lateinit var searchViewPager: ViewPager2
    private lateinit var searchTabLayout: TabLayout
    private lateinit var eTSearch: EditText
    private lateinit var icClear: ImageView
    private lateinit var icSearch: ImageView
    private val frList = listOf(repoSearch, userSearch, issueSearch)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        toolbar = findViewById(R.id.toolbar_for)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        searchViewPager = findViewById(R.id.search_view_pager)
        searchTabLayout = findViewById(R.id.search_tab_layout)
        eTSearch = findViewById(R.id.e_t_search)
        icClear = findViewById(R.id.ic_clear)
        icSearch = findViewById(R.id.ic_search)

        searchTabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        searchViewPager.adapter = ViewPagerAdapter(frList, supportFragmentManager, lifecycle)
        TabLayoutMediator(searchTabLayout, searchViewPager) { tab, position ->
            tab.text = tabTitle(position)
        }.attach()

        icClear.setOnClickListener {
            eTSearch.setText("")
        }
        eTSearch.addTextChangedListener(searchViewTextWatcher)
        icSearch.setOnClickListener {
            onSearchClicked()
        }
        eTSearch.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onSearchClicked()
            }
            return@OnEditorActionListener true
        })
    }

    private val searchViewTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (s!!.toString().isEmpty()) {
                icClear.visibility = View.GONE
            } else {
                icClear.visibility = View.VISIBLE
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private fun onSearchClicked() {
        if (eTSearch.text.toString().isNotEmpty()) {
            vm.queryForSearchApi(eTSearch.text.toString())
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun tabTitle(position: Int): String {
        return when (position) {
            0 -> "REPOSITORIES"
            1 -> "USERS"
            2 -> "ISSUES"
            else -> "NULL"
        }
    }

    companion object {
        private val userSearch = UserSearchFragment()
        private val repoSearch = RepoSearchFragment()
        private val issueSearch = IssueSearchFr()
    }
}