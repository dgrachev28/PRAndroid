package com.company.prandroid.util

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream


fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    return baos.toByteArray()
}
