package com.pritesh.mvvm_coroutines.repo

import com.pritesh.mvvm_coroutines.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestClient {

    companion object {
        private var retrofit: Retrofit? = Retrofit.Builder()
            .baseUrl(BuildConfig.apiEndPoint)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .readTimeout(2, TimeUnit.MINUTES)
                    .connectTimeout(2, TimeUnit.MINUTES)
                    .writeTimeout(2, TimeUnit.MINUTES)
                    .addInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                    .build()
            )
            .build()

        fun getInstance(): APIServices {
            return retrofit!!.create(APIServices::class.java)
        }
    }
}