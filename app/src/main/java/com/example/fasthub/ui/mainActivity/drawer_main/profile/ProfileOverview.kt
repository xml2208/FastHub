package com.example.fasthub.ui.mainActivity.drawer_main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.fasthub.GithubApp
import com.example.fasthub.databinding.ProfileOverviewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ProfileOverview : Fragment() {
    private var _vb: ProfileOverviewBinding? = null
    private val vb get() = _vb!!
    private val vm: ProfileViewModel by activityViewModels()

    private val formatter: SimpleDateFormat by lazy { SimpleDateFormat("MMM dd,yyyy", Locale.US) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = ProfileOverviewBinding.inflate(layoutInflater)
        return vb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            vm.img.collect { img ->
                vb.profileImg.load(img)
            }
        }
        lifecycleScope.launchWhenResumed {
            vm.userName.collect {
                vb.username.text = it
            }
        }
        lifecycleScope.launchWhenResumed {
            vm.profileName.collect {
                vb.profileName.text = it
            }
        }
        lifecycleScope.launchWhenResumed {
            vm.bio.collect { bio ->
                vb.userBio.text = bio
            }
        }
        lifecycleScope.launchWhenResumed {
            vm.location.collect { location ->
                vb.location.text = location
            }
        }
        lifecycleScope.launchWhenResumed {
            vm.createdAt.collect { time ->
                try {
                    vb.accessTime.text = formatter.format(time)
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "$e", Toast.LENGTH_LONG).show()
                }
            }
        }
        vb.accessToken.text = GithubApp.INSTANCE.prefs.token
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }
}