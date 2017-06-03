package com.company.prandroid.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.company.prandroid.BR
import com.company.prandroid.rest.BaseAPIService.Companion.apiHost
import com.company.prandroid.rest.BaseAPIService.Companion.apiPort
import com.company.prandroid.rest.BaseAPIService.Companion.buildBaseUrl
import com.company.prandroid.rest.status.StatusAPIService
import com.company.prandroid.util.SimpleTextWatcher


class ServerConnectionViewModel : BaseObservable() {

    @get:Bindable
    var host: String = apiHost
        set(value) {
            field = value
            notifyPropertyChanged(BR.host)
        }

    @get:Bindable
    var port: String = apiPort
        set(value) {
            field = value
            notifyPropertyChanged(BR.port)
        }

    @get:Bindable
    var connectionStatusMessage: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.connectionStatusMessage)
        }

    private val statusAPIService = StatusAPIService(this)

    fun onClickTest(view: View) {
        statusAPIService.status(buildBaseUrl(host, port))
    }

    fun onClickSave(view: View) {
        apiHost = host
        apiPort = port
        connectionStatusMessage = "Saved"
    }

    var hostWatcher: TextWatcher = object: SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable?) {
            if (host != s.toString()) {
                host = s.toString()
            }
        }
    }

    var portWatcher: TextWatcher = object: SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable?) {
            if (port != s.toString()) {
                port = s.toString()
            }
        }
    }

}
