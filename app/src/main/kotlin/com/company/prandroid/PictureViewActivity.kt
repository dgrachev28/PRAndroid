package com.company.prandroid

import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import com.company.prandroid.databinding.ActivityPictureViewBinding
import com.company.prandroid.dto.PictureDto
import com.company.prandroid.viewmodel.MainViewModel.Companion.PICTURE_DTO

class PictureViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPictureViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_picture_view)

        val pictureDto = intent.getSerializableExtra(PICTURE_DTO) as PictureDto
        val decodedImage = Base64.decode(pictureDto.imageBase64, Base64.DEFAULT)
        val bmp = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.size)
        binding.imageView.setImageBitmap(Bitmap.createBitmap(bmp))
        binding.viewModel = pictureDto
    }
}
