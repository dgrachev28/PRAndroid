package com.company.prandroid.viewmodel

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.view.View
import com.company.prandroid.PictureViewActivity
import com.company.prandroid.dto.PictureDto
import com.company.prandroid.rest.image.ImageAPIService
import com.company.prandroid.util.bitmapToByteArray

class MainViewModel(private val activity: Activity) {

    private val imageAPIService = ImageAPIService(this)

    val REQUEST_IMAGE_CAPTURE = 1

    fun onClickRecognize(view: View) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(activity.packageManager) != null) {
            activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    fun recognizeBitmap(bitmap: Bitmap) {
        val byteArray = bitmapToByteArray(bitmap)
        imageAPIService.recognize(byteArray)
    }

    fun startPictureViewActivity(pictureDto: PictureDto) {
        val intent = Intent(activity, PictureViewActivity::class.java)
        intent.putExtra("pictureDto", pictureDto)
        activity.startActivity(intent)
    }
}
