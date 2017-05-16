package com.company.prandroid.rest.image

import com.company.prandroid.dto.PictureDto
import com.company.prandroid.dto.Test
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import rx.Observable


interface ImageRest {
    @GET("image")
    fun test(): Observable<Test>

    @Multipart
    @POST("image/recognize")
    fun recognize(@Part image: MultipartBody.Part): Observable<PictureDto>
}
