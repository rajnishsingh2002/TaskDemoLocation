package com.simple.taskdemoapp.mapfragment

import com.simple.taskdemoapp.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.simple.taskdemoapp.mapfragment.adapter.StatusAdapter
import com.simple.taskdemoapp.mapfragment.viewmodel.StatusViewModel

class BottomSheetMapFragment : BottomSheetDialogFragment() {

    private lateinit var viewModel: StatusViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.activity_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.mapRecyclerView)
        viewModel = ViewModelProvider(requireActivity())[StatusViewModel::class.java]

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.statusList.observe(viewLifecycleOwner) {
            recyclerView.adapter = StatusAdapter(it)
        }
    }
}
