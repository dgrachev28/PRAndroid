package com.company.prandroid.rest.status

import android.util.Log
import com.company.prandroid.rest.BaseAPIService
import com.company.prandroid.viewmodel.ServerConnectionViewModel
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class StatusAPIService(private val serverConnectionViewModel: ServerConnectionViewModel) : BaseAPIService() {

    private val TAG = StatusAPIService::class.java.name

    fun status(host: String, port: String) {
        try {
            val service = buildRetrofit(host, port).create<StatusRest>(StatusRest::class.java)

            service.status()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        serverConnectionViewModel.connectionStatusMessage = "OK"
                    }, {
                        Log.e(TAG, it.message, it.cause)
                        serverConnectionViewModel.connectionStatusMessage = "Connection failed"
                    })
        } catch (e: IllegalArgumentException) {
            Log.e(TAG, e.message, e.cause)
            serverConnectionViewModel.connectionStatusMessage = "Illegal url"
        }
    }

}
