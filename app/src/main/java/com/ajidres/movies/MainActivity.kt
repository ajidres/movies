package com.ajidres.movies

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ajidres.movies.base.BaseActivity
import com.ajidres.movies.data.AppPreferences
import com.ajidres.movies.databinding.ActivityMainBinding
import com.ajidres.movies.extentions.PERMISSION_BACKGROUND_LOCATION
import com.ajidres.movies.extentions.PERMISSION_LOCATION
import com.ajidres.movies.extentions.PERMISSION_REQUEST_CODE
import com.ajidres.movies.extentions.askPermissions
import com.ajidres.movies.extentions.checkPermissions
import com.ajidres.movies.service.LocationUpdateService
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    @Inject
    lateinit var appPreferences: AppPreferences

    override fun initBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
    }

    override fun initView(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        askPermissions(PERMISSION_LOCATION)
        startServiceLocation()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==PERMISSION_REQUEST_CODE){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && checkPermissions(PERMISSION_LOCATION) && !checkPermissions(PERMISSION_BACKGROUND_LOCATION)){
                askPermissions(PERMISSION_BACKGROUND_LOCATION)
            }else{
                startServiceLocation()
            }
        }

    }

    private fun startServiceLocation(){
        if(checkPermissions(PERMISSION_LOCATION)){
            if(!appPreferences.tracking){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(Intent(this, LocationUpdateService::class.java))
                }else{
                    startService(Intent(this, LocationUpdateService::class.java))
                }
            }
        }
    }



}
