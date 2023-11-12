package com.ajidres.movies.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.IBinder
import android.os.Looper
import android.util.Log
import com.ajidres.movies.data.AppPreferences
import com.ajidres.movies.data.dbCloud.CLOUD_LOCATION
import com.ajidres.movies.data.dbCloud.LocationCloudStore
import com.ajidres.movies.data.dbCloud.toHasMap
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@AndroidEntryPoint
class LocationUpdateService : Service() {

    @Inject
    lateinit var appPreferences: AppPreferences

    private val locationTime = TimeUnit.SECONDS.toMillis(30)

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private var currentLocation: Location? = null


    @SuppressLint("MissingPermission")
    override fun onCreate() {
        super.onCreate()


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.Builder(locationTime)
            .setMinUpdateIntervalMillis(locationTime)
            .setMaxUpdateDelayMillis(locationTime)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .build()


        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                currentLocation = locationResult.lastLocation

                if (currentLocation == null ||
                    currentLocation?.latitude == null ||
                    currentLocation?.longitude == null
                ) return

                Firebase.firestore.collection(CLOUD_LOCATION)
                    .add(
                        LocationCloudStore(
                            lat = currentLocation?.latitude!!,
                            long = currentLocation?.longitude!!
                        ).toHasMap()
                    )
                    .addOnSuccessListener { documentReference ->
                        Log.e("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                    }
                    .addOnFailureListener { e ->
                        Log.w("TAG", "Error adding document", e)
                    }


            }
        }

        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback, Looper.getMainLooper()
        )
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        appPreferences.tracking = true
        return START_STICKY
    }


}