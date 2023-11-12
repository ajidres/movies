package com.ajidres.movies.extentions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

const val PERMISSION_REQUEST_CODE = 1001
val PERMISSION_LOCATION = listOf(
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.ACCESS_FINE_LOCATION
)

val PERMISSION_CAMERA_ARRAY = arrayOf(
    Manifest.permission.CAMERA,
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)

val PERMISSION_CAMERA = listOf(
    Manifest.permission.CAMERA
)

val PERMISSION_CAMERA_OLD = listOf(
    Manifest.permission.CAMERA,
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)

@RequiresApi(Build.VERSION_CODES.Q)
val PERMISSION_BACKGROUND_LOCATION = listOf(
    Manifest.permission.ACCESS_BACKGROUND_LOCATION
)





fun Activity.askPermissions(permission: List<String>) {
    val permissionsList: MutableList<String> = mutableListOf()

    for (perm in permission) {
        when {
            ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED -> {
                permissionsList.add(perm)
            }

            shouldShowRequestPermissionRationale(perm) -> {
                Log.e("TAG", "askPermissions: ")
            }

            else -> {

            }
        }
    }

    if (permissionsList.size > 0) {


        requestPermissions(permissionsList.toTypedArray(), PERMISSION_REQUEST_CODE)

    }

}

fun Fragment.checkPermissions(permission: List<String>):Boolean {
    return activity?.checkPermissions(permission)!!
}


fun Activity.checkPermissions(permission: List<String>): Boolean {

    val permissionsList: MutableList<String> = mutableListOf()

    for (perm in permission) {
        if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(perm)
        }
    }

    return permissionsList.isEmpty()
}
