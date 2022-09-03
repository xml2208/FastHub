package com.example.fasthub.ui.mainActivity.drawer_main.pinned

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fasthub.R
import com.example.fasthub.databinding.FragmentPinnedGistsBinding

class PinnedGistsFragment : Fragment() {
    private var _vb: FragmentPinnedGistsBinding? = null
    private val vb get() = _vb!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentPinnedGistsBinding.inflate(layoutInflater)
        return vb.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }

    companion object {
        fun newInstance(): PinnedGistsFragment {
            return PinnedGistsFragment()
        }
    }
}