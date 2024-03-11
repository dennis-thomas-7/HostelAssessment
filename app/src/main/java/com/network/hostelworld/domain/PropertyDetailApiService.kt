package com.network.hostelworld.domain

import android.content.Context
import com.network.hostelworld.data.CurrencyDataResponse
import com.network.hostelworld.data.NetworkConnectionInterceptor
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface PropertyDetailApiService {
    @GET("latest?access_key=81e7cc10af6fa7a031c73b4f68f3b3cb")
    fun getCurrencyData(): Single<CurrencyDataResponse>

    companion object {
        fun create(context:Context): PropertyDetailApiService {
            val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(NetworkConnectionInterceptor(context))
                .build()
            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.exchangeratesapi.io/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()

            return retrofit.create(PropertyDetailApiService::class.java)
        }
    }

}