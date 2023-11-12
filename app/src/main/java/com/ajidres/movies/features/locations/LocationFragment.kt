package com.ajidres.movies.features.locations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.ajidres.movies.R
import com.ajidres.movies.base.BaseFragment
import com.ajidres.movies.databinding.FragmentLocationBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LocationFragment:BaseFragment<FragmentLocationBinding>(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private val viewModel: LocationViewModel by viewModels()

    override fun initBinding(): FragmentLocationBinding = FragmentLocationBinding.inflate(layoutInflater)

    override fun initView(view: View, savedInstanceState: Bundle?) {
        setMap()
        locationObserverApi()
        viewModel.fetchLocations()
    }

    private fun setMap(){
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    private fun locationObserverApi() {
        val zoomLevel = 16.0f
        viewModel.locationData.observe(this) { locations ->
            locations.forEach {
                val location = LatLng(it.lat,it.long)
                mMap.addMarker(
                    MarkerOptions()
                    .position(location))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
            }
        }
    }

    companion object{
        fun newInstance():LocationFragment {
            return LocationFragment()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap=googleMap
        val uiSettings = googleMap.uiSettings
        uiSettings.isZoomControlsEnabled = true


    }
}