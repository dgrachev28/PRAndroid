package com.company.prandroid

import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import com.company.prandroid.databinding.ActivityPictureViewBinding
import com.company.prandroid.dto.PictureDto
import com.company.prandroid.util.BitmapUtils
import com.company.prandroid.viewmodel.MainViewModel.Companion.IMAGE_FILE_PATH
import com.company.prandroid.viewmodel.MainViewModel.Companion.PICTURE_DTO
import com.company.prandroid.viewmodel.MainViewModel.Companion.PICTURE_TITLE

class PictureViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPictureViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_picture_view)

        val bitmap = BitmapUtils.loadImageFromStorage(intent.getStringExtra(IMAGE_FILE_PATH))

//        val pictureDto = intent.getSerializableExtra(PICTURE_DTO) as PictureDto
//        val decodedImage = Base64.decode(pictureDto.imageBase64, Base64.DEFAULT)
//        val bmp = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.size)
        binding.imageView.setImageBitmap(bitmap)
        binding.viewModelTitle = intent.getStringExtra(PICTURE_TITLE)
    }
}
