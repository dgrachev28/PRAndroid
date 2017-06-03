package com.company.prandroid.rest.status

import retrofit2.http.GET
import rx.Observable


interface StatusRest {
    @GET("status")
    fun status(): Observable<Void>
}
