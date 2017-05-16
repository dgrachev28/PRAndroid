package com.company.prandroid


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.company.prandroid.dto.PictureDto
import com.company.prandroid.rest.image.ImageAPIService
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1

    lateinit var imageAPIService: ImageAPIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageAPIService = ImageAPIService(this)
        setContentView(R.layout.activity_main)
        val recognizeButton = findViewById(R.id.recognizeButton) as Button
        recognizeButton.setOnClickListener { dispatchTakePictureIntent() }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
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
