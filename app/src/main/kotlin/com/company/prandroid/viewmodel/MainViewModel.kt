package com.company.prandroid.viewmodel

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import android.view.View

class MainViewModel(activ: Activity) {

    val REQUEST_IMAGE_CAPTURE = 1
    private val activity = activ

    fun onClickRecognize(view: View) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(activity.packageManager) != null) {
            activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }
}
