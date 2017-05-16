package com.company.prandroid.rest.image

import com.company.prandroid.MainActivity
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class ImageAPIService(mainActivity: MainActivity) {

    private val activity = mainActivity

    private val retrofit: Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.1.154:8080/")
            .build()

    fun test() {
        val service = retrofit.create<ImageRest>(ImageRest::class.java)

        service.test()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { println(it.name) }
    }

    fun recognize(byteArray: ByteArray) {
        val request = RequestBody.create(MediaType.parse("image/jpeg"), byteArray)
        val body = MultipartBody.Part.createFormData("image", "photo.jpeg", request)

        val service = retrofit.create<ImageRest>(ImageRest::class.java)

        service.recognize(body)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    println(it.title)
                    activity.startPictureViewActivity(it)
                }
    }

}
