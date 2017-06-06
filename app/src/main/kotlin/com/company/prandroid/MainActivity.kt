package com.company.prandroid


import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.company.prandroid.databinding.ActivityMainBinding
import com.company.prandroid.rest.BaseAPIService.Companion.DEFAULT_HOST
import com.company.prandroid.rest.BaseAPIService.Companion.DEFAULT_PORT
import com.company.prandroid.rest.BaseAPIService.Companion.apiHost
import com.company.prandroid.rest.BaseAPIService.Companion.apiPort
import com.company.prandroid.viewmodel.MainViewModel
import com.company.prandroid.viewmodel.MainViewModel.Companion.REQUEST_IMAGE_CAPTURE


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val PREFS_NAME = "MyPrefsFile"
    private val API_HOST_KEY = "apiHost"
    private val API_PORT_KEY = "apiPort"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = MainViewModel(this)

        val settings = getSharedPreferences(PREFS_NAME, 0)
        apiHost = settings.getString(API_HOST_KEY, DEFAULT_HOST)
        apiPort = settings.getString(API_PORT_KEY, DEFAULT_PORT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            binding.viewModel.recognizeBitmap(data!!.extras.get("data") as Bitmap)
        }
    }

    override fun onStop() {
        super.onStop()
        saveHostAndPort()
    }

    private fun saveHostAndPort() {
        val settings = getSharedPreferences(PREFS_NAME, 0)
        val editor = settings.edit()
        editor.putString(API_HOST_KEY, apiHost)
        editor.putString(API_PORT_KEY, apiPort)
        editor.apply()
    }

}
