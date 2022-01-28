package com.pritesh.mvvm_coroutines.repo

import com.pritesh.mvvm_coroutines.ui.main.models.AleList
import retrofit2.Response
import retrofit2.http.GET


interface APIServices {
    @GET("/beers/ale")
    suspend fun getAleList(): Response<List<AleList>>?
}