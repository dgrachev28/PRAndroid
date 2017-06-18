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


class PictureViewViewModel: BaseObservable() {

    var title: String? = null
    var description: String? = null
    var year: String? = null
    var shortInfo: String? = null
    var gallery: String? = null
    var imagePath: String? = null

    private val statusAPIService = StatusAPIService(this)



}
