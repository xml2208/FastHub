package com.example.fasthub.ui.mainActivity.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fasthub.databinding.CodeSearchFrBinding

class CodeSearchFr: Fragment() {
    private var _vb: CodeSearchFrBinding? = null
    private val vb get() = _vb!!
    private val vm: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = CodeSearchFrBinding.inflate(layoutInflater)

        vm.getArgument(arguments?.getString("query") ?: "")
        vm.query.observe(viewLifecycleOwner) {
            vb.query.text = it
        }
        return vb.root
    }
    companion object {
        fun newInstance(): CodeSearchFr {
            return CodeSearchFr()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
        arguments?.clear()
    }
}