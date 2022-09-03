package com.example.fasthub

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.fasthub.databinding.FragmentSimple2Binding
import com.example.fasthub.ui.mainActivity.MainActivity
import com.example.fasthub.ui.mainActivity.adapters.ViewPagerAdapter
import com.example.fasthub.ui.mainActivity.drawer_main.pinned.PinnedFragment
import com.example.fasthub.ui.mainActivity.drawer_main.profile.ProfileFragment.Companion.list
import com.example.fasthub.ui.mainActivity.drawer_main.profile.ProfileRepositoriesFragment
import com.example.fasthub.ui.mainActivity.drawer_main.profile.ProfileViewModel
import com.example.fasthub.ui.mainActivity.drawer_main.profile.StarredReposFragment
import com.example.fasthub.ui.mainActivity.drawer_profile.AddAccountFr
import com.example.fasthub.ui.oauth.OAuthActivity
import com.google.android.material.navigation.NavigationView

class SimpleFragment2 : Fragment() {
    companion object {
        val addAccount = AddAccountFr.newInstance()
        val pinnedFragment = PinnedFragment.newInstance()
    }

    private var _vb: FragmentSimple2Binding? = null
    private val vb get() = _vb!!
    private val vm: ProfileViewModel by activityViewModels()
    private lateinit var navigationView: NavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _vb = FragmentSimple2Binding.inflate(layoutInflater)
        navigationView = vb.simpleNav2
        navigationView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.logout -> showAlertDialog()
                R.id.add_account -> setCurrentFragment(addAccount)
                R.id.repos -> setCurrentFragment(ProfileRepositoriesFragment())
                R.id.starred -> setCurrentFragment(StarredReposFragment())
                R.id.pin -> setCurrentFragment(pinnedFragment)
            }
            return@OnNavigationItemSelectedListener true
        })
        return vb.root
    }

    private fun setCurrentFragment(fragment: Fragment) {
        (activity as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment).commit()
        (activity as MainActivity).drawer.closeDrawer(GravityCompat.START)
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Logout").setMessage("Are you sure?")
                .setPositiveButton("Yes") { _, _ -> onYesClicked() }
                .setNegativeButton("No") { _, _ ->
                    Toast.makeText(
                        requireContext(), "no clicked", Toast.LENGTH_SHORT
                    ).show()
                }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.apply {
                setOnShowListener {
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED)
                }
                show()
                window?.apply {
                    setGravity(Gravity.BOTTOM)
                    setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
                }
            }
        }
    }

    private fun onYesClicked() {
        GithubApp.INSTANCE.prefs.deleteToken()
        val intent = Intent(requireActivity(), OAuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
//        try {
//            vm.revokeAccess()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }
}