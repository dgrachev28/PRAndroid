package com.company.prandroid.viewmodel

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.support.v4.app.FragmentActivity
import android.util.Base64
import android.view.View
import com.company.prandroid.PictureViewActivity
import com.company.prandroid.ServerConnectionActivity
import com.company.prandroid.dto.PictureDto
import com.company.prandroid.fragment.NetworkConnectionDialogFragment
import com.company.prandroid.rest.image.ImageAPIService
import com.company.prandroid.util.BitmapUtils
import com.company.prandroid.util.bitmapToByteArray

class MainViewModel(private val activity: FragmentActivity) {

    companion object {
        val REQUEST_IMAGE_CAPTURE = 1
        val PICTURE_DTO = "pictureDto"
        val PICTURE_TITLE = "pictureTitle"
        val IMAGE_FILE_PATH = "imageFilePath"
    }

    private val imageAPIService = ImageAPIService(this)

    fun onClickRecognize(view: View) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(activity.packageManager) != null) {
            activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    fun onClickSettings(view: View) {
        val intent = Intent(activity, ServerConnectionActivity::class.java)
        activity.startActivity(intent)
    }

    fun recognizeBitmap(bitmap: Bitmap) {
        val byteArray = bitmapToByteArray(bitmap)
        imageAPIService.recognize(byteArray)
    }

    fun startPictureViewActivity(pictureDto: PictureDto) {
        val intent = Intent(activity, PictureViewActivity::class.java)
//        intent.putExtra(PICTURE_DTO, pictureDto)
        val decodedImage = Base64.decode(pictureDto.imageBase64, Base64.DEFAULT)
        val bmp = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.size)
        intent.putExtra(PICTURE_TITLE, pictureDto.title)
        intent.putExtra(IMAGE_FILE_PATH, BitmapUtils.saveToInternalStorage(bmp, activity))
        activity.startActivity(intent)
    }

    fun showNetworkErrorDialog() {
        NetworkConnectionDialogFragment().show(activity.supportFragmentManager, "Dialog")
    }
}
