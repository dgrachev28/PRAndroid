package com.company.prandroid

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.company.prandroid.databinding.ActivityServerConnectionBinding
import com.company.prandroid.viewmodel.ServerConnectionViewModel

class ServerConnectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServerConnectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_server_connection)
        binding.viewModel = ServerConnectionViewModel()
    }

}