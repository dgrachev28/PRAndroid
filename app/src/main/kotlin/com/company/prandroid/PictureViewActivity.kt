package com.company.prandroid

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.widget.ImageView
import com.company.prandroid.dto.PictureDto

class PictureViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_view)

        val pictureDto = intent.getSerializableExtra("pictureDto") as PictureDto
        println(pictureDto.title)

        val decodedImage = Base64.decode(pictureDto.imageBase64, Base64.DEFAULT)
        val bmp = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.size)
        val imageView = findViewById(R.id.imageView) as ImageView
        imageView.setImageBitmap(Bitmap.createBitmap(bmp))
    }
}
