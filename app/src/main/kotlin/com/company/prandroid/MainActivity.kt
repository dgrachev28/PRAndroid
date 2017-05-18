package com.company.prandroid


import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.company.prandroid.databinding.ActivityMainBinding
import com.company.prandroid.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = MainViewModel(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            binding.viewModel.recognizeBitmap(data.extras.get("data") as Bitmap)
        }
    }

}
