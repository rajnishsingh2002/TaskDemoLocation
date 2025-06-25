package com.simple.taskdemoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var viewModel: StatusViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)


        // Setup Bottom Sheet
        val bottomSheet = view.findViewById<LinearLayout>(R.id.bottom_sheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.peekHeight = 150
        bottomSheetBehavior.isFitToContents = false
        bottomSheetBehavior.expandedOffset = 100
        bottomSheetBehavior.halfExpandedRatio = 0.7f
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED



        // Setup RecyclerView
//        val recyclerView = view.findViewById<RecyclerView>(R.id.mapRecyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        recyclerView.adapter = ItemAdapter(List(20) { "Place ${it + 1}" })

        val recyclerView = view.findViewById<RecyclerView>(R.id.mapRecyclerView)
        viewModel = ViewModelProvider(this)[StatusViewModel::class.java]

        viewModel.statusList.observe(viewLifecycleOwner) { list ->
            recyclerView.adapter = StatusAdapter(list)
            recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        }

        viewModel.fetchStatus()




        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)



        return view
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val location = LatLng(28.6139, 77.2090) // Delhi
        googleMap.addMarker(MarkerOptions().position(location).title("Marker in Delhi"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
    }
}
