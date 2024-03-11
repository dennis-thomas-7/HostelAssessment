package com.network.hostelworld.domain

import android.content.Context
import com.network.hostelworld.data.NetworkConnectionInterceptor
import com.network.hostelworld.data.PropertyResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface PropertyApiService {
    @GET(".")
    fun getProperties(): Single<PropertyResponse>

    companion object {
        fun create(context:Context): PropertyApiService {
            val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(NetworkConnectionInterceptor(context))
                .build()
            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://gist.githubusercontent.com/PedroTrabulo-Hostelworld/a1517b9da90dd6877385a65f324ffbc3/raw/058e83aa802907cb0032a15ca9054da563138541/") // Change this to your API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()

            return retrofit.create(PropertyApiService::class.java)
        }
    }



}

