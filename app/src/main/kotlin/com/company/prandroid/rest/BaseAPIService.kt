package com.company.prandroid.rest

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


open class BaseAPIService {

    companion object {

        val DEFAULT_HOST = "192.168.1.154"
        val DEFAULT_PORT= "8080"

        @Volatile var apiHost: String = DEFAULT_HOST
        @Volatile var apiPort: String = DEFAULT_PORT
        fun buildBaseUrl(host: String, port: String) = "http://$host:$port/"

        fun buildRetrofit(): Retrofit {
            return buildRetrofit(apiHost, apiPort)
        }

        fun buildRetrofit(host: String, port: String): Retrofit {
            return buildRetrofit(buildBaseUrl(host, port))
        }

        private fun buildRetrofit(url: String): Retrofit {
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