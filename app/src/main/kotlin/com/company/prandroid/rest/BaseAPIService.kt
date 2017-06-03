package com.company.prandroid.rest

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


open class BaseAPIService {

    companion object {

        @Volatile var apiHost: String = "192.168.1.154"
        @Volatile var apiPort: String = "8080"
        fun buildBaseUrl() = "http://$apiHost:$apiPort/"
        fun buildBaseUrl(host: String, port: String) = "http://$host:$port/"

        fun buildRetrofit(): Retrofit {
            return buildRetrofit(buildBaseUrl())
        }

        fun buildRetrofit(url: String): Retrofit {
            val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(1, TimeUnit.SECONDS)
                    .connectTimeout(1, TimeUnit.SECONDS)
                    .build()
            return Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(url)
                    .client(okHttpClient)
                    .build()
        }
    }

}