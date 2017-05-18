package com.company.prandroid.dto

import java.io.Serializable

data class PictureDto(
        val title: String,
        val description: String,
        val year: String,
        val shortInfo: String,
        val gallery: String,
        val imageBase64: String) : Serializable