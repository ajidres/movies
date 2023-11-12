package com.ajidres.movies.features.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ajidres.movies.data.dbCloud.CLOUD_LOCATION
import com.ajidres.movies.data.dbCloud.LocationCloudStore
import com.ajidres.movies.data.dbCloud.toLocationCloudStore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LocationViewModel : ViewModel() {

    private var _locationData = MutableLiveData<List<LocationCloudStore>>()
    val locationData: LiveData<List<LocationCloudStore>> get() = _locationData

    private val listLocations = mutableListOf<LocationCloudStore>()

    fun fetchLocations() {
        Firebase.firestore.collection(CLOUD_LOCATION)
            .get()
            .addOnSuccessListener { locations ->
                locations.forEach {
                    listLocations.add(
                        it.data.toLocationCloudStore()
                    )
                }
                _locationData.value = listLocations.toList()
            }
            .addOnFailureListener { _ -> }
    }
}