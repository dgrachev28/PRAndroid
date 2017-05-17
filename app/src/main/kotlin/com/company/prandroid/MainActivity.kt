package com.company.prandroid


import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.company.prandroid.databinding.ActivityMainBinding
import com.company.prandroid.dto.PictureDto
import com.company.prandroid.rest.image.ImageAPIService
import com.company.prandroid.viewmodel.MainViewModel
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1

    private lateinit var imageAPIService: ImageAPIService
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageAPIService = ImageAPIService(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = MainViewModel(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val extras = data.extras
            val imageBitmap = extras.get("data") as Bitmap
            val byteArray = bitmapToByteArray(imageBitmap)
            imageAPIService.recognize(byteArray)
        }
    }

    fun startPictureViewActivity(pictureDto: PictureDto) {
        val intent = Intent(this, PictureViewActivity::class.java)
        intent.putExtra("pictureDto", pictureDto)
        startActivity(intent)
    }

    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        return baos.toByteArray()
    }

}
