package com.ajidres.movies.features.gallery

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.ajidres.movies.R
import com.ajidres.movies.base.BaseFragment
import com.ajidres.movies.databinding.FragmentGalleryBinding
import com.ajidres.movies.extentions.PERMISSION_CAMERA
import com.ajidres.movies.extentions.PERMISSION_CAMERA_ARRAY
import com.ajidres.movies.extentions.PERMISSION_CAMERA_OLD
import com.ajidres.movies.extentions.checkPermissions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date


class GalleryFragment : BaseFragment<FragmentGalleryBinding>() {

    private val storageRef = Firebase.storage.reference
    private lateinit var fileFromCamera:String
    private lateinit var photoFile:File

    override fun initBinding(): FragmentGalleryBinding = FragmentGalleryBinding.inflate(layoutInflater)

    override fun initView(view: View, savedInstanceState: Bundle?) {


        binding.btnGallery.setOnClickListener { loadGallery() }
        binding.btnPhoto.setOnClickListener { openCamera() }
    }

    private fun loadGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"
        imagePickerActivityResult.launch(galleryIntent)
    }

    private fun openCamera() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            Environment.isExternalStorageManager() && checkPermissions(PERMISSION_CAMERA)
        }else{
            checkPermissions(PERMISSION_CAMERA_OLD)
        }

        if (permission) {
            takePhoto()
        } else {
            requestPermissionStorage()
        }
    }

    private fun takePhoto(){
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        photoFile = createImageFile()

        val photoURI: Uri = FileProvider.getUriForFile(
            requireActivity(),
            "${requireActivity().packageName}.provider",
            photoFile)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)


        takePhotoActivityResult.launch(takePictureIntent)
    }

    private fun requestPermissionStorage(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            try {
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
                val uri = Uri.fromParts("package", requireActivity().packageName, null)
                intent.data = uri
                storageActivityResultLauncher.launch(intent)
            }
            catch (e: Exception){
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                storageActivityResultLauncher.launch(intent)
            }
        }
        else{
            requestMultiplePermissions.launch(PERMISSION_CAMERA_ARRAY)
        }
    }

    private val storageActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            if (Environment.isExternalStorageManager()){
                requestMultiplePermissions.launch(PERMISSION_CAMERA.toTypedArray())
            }
        }
    }

    private val requestMultiplePermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
        openCamera()
    }


    private val takePhotoActivityResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result != null) {
                loadImageToFirebase(fileFromCamera, Uri.fromFile(photoFile))
            }
        }

    private val imagePickerActivityResult: ActivityResultLauncher<Intent> =

        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result != null) {

                val imageUri: Uri? = result.data?.data
                if(imageUri!=null){
                    val fileName = getFileName(requireActivity(), imageUri)
                    loadImageToFirebase(fileName!!, imageUri)
                }


            }
        }


    private fun loadImageToFirebase(fileName:String, imageUri:Uri){
        val uploadTask = storageRef.child("images/$fileName").putFile(imageUri)

        uploadTask.addOnSuccessListener {
            Toast.makeText(requireActivity(), getString(R.string.image_loaded), Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(requireActivity(), getString(R.string.image_load_fail), Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = Date().time.toString()
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        fileFromCamera = "IMG_${timeStamp}.jpg"
        return File.createTempFile(
            "IMG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }



    @SuppressLint("Range")
    private fun getFileName(context: Context, uri: Uri): String? {
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            cursor.use {
                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        return cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                    }
                }
            }
        }
        return uri.path?.lastIndexOf('/')?.let { uri.path?.substring(it) }
    }


    companion object {
        fun newInstance(): GalleryFragment {
            return GalleryFragment()
        }
    }
}