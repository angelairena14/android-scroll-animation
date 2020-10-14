package com.example.cermati.networks

import android.content.Context
import com.example.cermati.Constants
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

class HeaderInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        var requestBuilder = request.newBuilder()
            .addHeader("Authorization","token "+Constants.GITHUB_TOKEN)
        request = requestBuilder.build()

        var response= chain.proceed(request)
        var stringData = ""
        response.body()?.string()?.let { json ->
            stringData = json
        }
        val contentType = response.body()?.contentType()
        val body = ResponseBody.create(contentType,stringData)
        return response.newBuilder().body(body).build()
    }
}