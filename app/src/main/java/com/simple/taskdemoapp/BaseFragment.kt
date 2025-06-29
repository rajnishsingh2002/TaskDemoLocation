package com.simple.taskdemoapp

import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
}