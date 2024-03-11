package com.network.hostelworld.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor (val context: Context?) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isConnected()) {
            throw NoConnectivityException()
        }else{
            val request = chain.request()

            val startTime = System.currentTimeMillis()
            val response = chain.proceed(request)
            val endTime = System.currentTimeMillis()

            val duration = endTime - startTime

            logNetworkStats(request.url.toString(), duration)

            return response
        }

    }

    fun isConnected() : Boolean {
        val netInfo = (context?.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager).allNetworkInfo
        for(info in netInfo) {
            if(info?.state == NetworkInfo.State.CONNECTED) {
                return true
            }
        }
        return false
    }

    private fun logNetworkStats(url: String, duration: Long) {

         Log.d("NetworkStats", "URL: $url, Duration: $duration ms")
    }
}