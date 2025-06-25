package com.simple.taskdemoapp.mapfragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.simple.taskdemoapp.R
import com.simple.taskdemoapp.mapfragment.adapter.StatusAdapter
import com.simple.taskdemoapp.mapfragment.model.StatusModel
import com.simple.taskdemoapp.mapfragment.viewmodel.StatusViewModel

class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var viewModel: StatusViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)


        // Setup Bottom Sheet
        val bottomSheet = view.findViewById<LinearLayout>(R.id.bottom_sheet_map)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.peekHeight = 150
        bottomSheetBehavior.isFitToContents = false
        bottomSheetBehavior.expandedOffset = 100
        bottomSheetBehavior.halfExpandedRatio = 0.7f
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED



        // Setup RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.mapRecyclerView)
        viewModel = ViewModelProvider(this)[StatusViewModel::class.java]


        viewModel.statusList.observe(viewLifecycleOwner) { list ->
            recyclerView.adapter = StatusAdapter(list)
            recyclerView.layoutManager = LinearLayoutManager(requireActivity())
            drawPathOnMap(list)
        }


        viewModel.fetchStatus()




        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)



        return view
    }

    private fun drawPathOnMap(dataList: List<StatusModel>) {
        if (!::mMap.isInitialized || dataList.isEmpty()) return

        val pathPoints = mutableListOf<LatLng>()

        for ((index, data) in dataList.withIndex()) {
            val latLng = LatLng(data.latitude, data.longitude)
            pathPoints.add(latLng)

            val label = when (index) {
                0 -> "Source"
                dataList.lastIndex -> "Destination"
                else -> "Waypoint ${index}"
            }

            mMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(label)
                    .snippet("Time: ${data.currentTime}\nStatus: ${data.workStatus}")
            )
        }

        mMap.addPolyline(
            PolylineOptions()
                .addAll(pathPoints)
                .color(Color.BLUE)
                .width(10f)
        )

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pathPoints.first(), 6f))
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

      }


}
