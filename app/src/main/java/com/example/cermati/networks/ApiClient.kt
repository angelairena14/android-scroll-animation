package com.example.cermati.networks

import android.app.Application
import android.content.Context
import com.example.cermati.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient : Application() {
    var defaultTimeOut : Long = 30

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HeaderInterceptor())
        .readTimeout(defaultTimeOut, TimeUnit.SECONDS)
        .writeTimeout(defaultTimeOut, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.GITHUB_API_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val services: ApiInterface = retrofit.create(ApiInterface::class.java)
}