package com.network.hostelworld.data

import com.network.hostelworld.domain.PropertyDetailApiService
import io.reactivex.rxjava3.core.Single

class PropertyDetailRepository (private val apiDetailService: PropertyDetailApiService) {

    fun getCurrencyData(): Single<CurrencyDataResponse> {
        return apiDetailService.getCurrencyData()
    }

}