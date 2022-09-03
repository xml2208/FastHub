package com.example.fasthub.ui.mainActivity.drawer_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.fasthub.databinding.AddAccountFrBinding

class AddAccountFr :Fragment() {
    private var _vb: AddAccountFrBinding? = null
    private val vb get() = _vb!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = AddAccountFrBinding.inflate(layoutInflater)
        vb.purchaseAll.setOnClickListener {
            showPurchaseDialog()
        }
        return vb.root
    }

    private fun showPurchaseDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Error")
            .setMessage("The item you requested is not available for purchase")
            .setPositiveButton("OK") {_, _, ->
//                Toast.makeText(requireContext(), "clicked ok", Toast.LENGTH_SHORT).show()
            }
            .show()
    }
    companion object {
        fun newInstance(): AddAccountFr =
            AddAccountFr()
    }
}