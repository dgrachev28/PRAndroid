package com.company.prandroid.rest.image

import android.util.Log
import com.company.prandroid.rest.BaseAPIService
import com.company.prandroid.rest.BaseAPIService.Companion.buildRetrofit
import com.company.prandroid.viewmodel.MainViewModel
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class ImageAPIService(private val mainViewModel: MainViewModel) : BaseAPIService() {

    private val TAG = ImageAPIService::class.java.name

    private val retrofit: Retrofit = buildRetrofit()

    fun recognize(byteArray: ByteArray) {
        val request = RequestBody.create(MediaType.parse("image/jpeg"), byteArray)
        val body = MultipartBody.Part.createFormData("image", "photo.jpeg", request)

        val service = retrofit.create<ImageRest>(ImageRest::class.java)

        service.recognize(body)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mainViewModel.startPictureViewActivity(it)
                }, {
                    Log.e(TAG, it.message, it.cause)
                    mainViewModel.showNetworkErrorDialog()
                })
    }

}
