package com.simple.taskdemoapp.homefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.simple.taskdemoapp.R

class HomeFragment : Fragment() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val bottomSheet = view.findViewById<LinearLayout>(R.id.bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)

        bottomSheetBehavior.peekHeight = 150
        bottomSheetBehavior.isFitToContents = false
        bottomSheetBehavior.expandedOffset = 100
        bottomSheetBehavior.halfExpandedRatio = 0.7f
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        return view
    }
}